package com.fu.bookshop.service.impl;
import com.fu.bookshop.dto.*;
import com.fu.bookshop.entity.Book;
import com.fu.bookshop.entity.Category;
import com.fu.bookshop.entity.Publisher;
import com.fu.bookshop.enums.BookStatus;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.exception.SystemException;
import com.fu.bookshop.repository.BookRepository;
import com.fu.bookshop.repository.CategoryRepository;
import com.fu.bookshop.repository.PublisherRepository;
import com.fu.bookshop.repository.ShopRepository;
import com.fu.bookshop.service.BookService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "BOOK-SERVICE-IMPL")
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final S3ServiceImpl s3Service;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;
    private final ShopRepository shopRepository;



    private BookCardDTO mapToCardDTO (Book book){
        if(book == null) return  null;

        return BookCardDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .image(book.getUrlImage())
                .price(book.getPrice())
                .oldPrice(null)
                .build();
    }

    @Override
    public BookDetailDTO getBookDetail(Long bookId) {
        Book book = bookRepository.findDetailById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        return BookDetailDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .image(book.getUrlImage())
                .stock(book.getQuantity())
                .price(book.getPrice())
                .description(book.getDescription())
                .authors(book.getAuthors())
                .publisherName(book.getPublisher().getName())
                .categoryNames(book.getCategories().stream().map(cate -> String.valueOf(cate.getName())).collect(Collectors.joining(",")))
                .isbn(book.getIsbn())
                .publicationDate(book.getPublicationDate() != null ? book.getPublicationDate() : null)
                .weight(
                        book.getWeight() != null
                                ? book.getWeight().intValue()
                                : null
                )
                .build();
    }

    @Override
    public List<BookCardDTO> getRelatedBooks(Long bookId) {
        Book book = bookRepository.findDetailById(bookId).orElseThrow(() -> new RuntimeException());
        List<Long> categoryIds = book.getCategories()
                .stream()
                .map(Category::getId)
                .toList();

        List<Book> books = bookRepository.findRelatedByCategories(categoryIds,
                bookId,
                BookStatus.ACTIVE,
                PageRequest.of(0, 10)
        );

        if (books.size() < 5) {
            books.addAll(bookRepository.findByPublisherIdAndIdNotAndStatus(
                            book.getPublisher().getId(),
                            bookId,
                            BookStatus.ACTIVE,
                            PageRequest.of(0, 10)
                    )
            );
        }

        return  books.stream()
                .distinct()
                .limit(10)
                .map(this::mapToCardDTO)
                .toList();
    }

    @Override
    public Page<BookListDTO> getFilteredBooks(
            String bookStatus,
            Long publisherId,
            List<Long> categoryIds,
            String keyword,
            int page,
            int size
    ) {

        // 1. BookStatus
        BookStatus statusEnum = null;
        try {
            if (bookStatus != null && !"ALL".equalsIgnoreCase(bookStatus)) {
                statusEnum = BookStatus.valueOf(bookStatus.toUpperCase());
            }
        } catch (IllegalArgumentException ignored) {}

        // 2. Publisher: 0 → null
        if (publisherId != null && publisherId == 0) {
            publisherId = null;
        }

        // 3. Category: chứa 0 → null
        if (categoryIds != null) {
            if (categoryIds.contains(0L)) {
                categoryIds = null;
            } else if (categoryIds.isEmpty()) {
                categoryIds = null;
            }
        }

        // 4. Keyword
        if (keyword != null && keyword.trim().isEmpty()) {
            keyword = null;
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<Book> pageResult =
                bookRepository.findFilteredBooks(
                        statusEnum,
                        publisherId,
                        categoryIds,
                        keyword,
                        pageable
                );

        return pageResult.map(this::toBookListDTO);
    }

    private BookListDTO toBookListDTO(Book b) {
        return BookListDTO.builder()
                .id(b.getId())
                .title(b.getTitle())
                .price(b.getPrice())
                .authors(b.getAuthors())
                .isbn(b.getIsbn())
                .publisherName(b.getPublisher().getName())
                .categoryNames(
                        b.getCategories().stream()
                                .map(Category::getName)
                                .collect(Collectors.joining(", "))
                )
                .quantity(b.getQuantity())
                .status(b.getStatus())
                .urlImage(b.getUrlImage())
                .build();
    }


    @Transactional
    @Override
    public void createBook(
            BookCreateRequest req,
            MultipartFile image
    )  {

        // 1. Check ISBN duplicate
        if (bookRepository.existsByIsbn(req.getIsbn())) {
            throw new BusinessException(ErrorCode.ISBN_DUPLICATE);
        }

        // 2. Upload image lên S3
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = s3Service.uploadFile(image);
        }

        // 3. Lấy category
        List<Category> categories =
                categoryRepository.findAllById(req.getCategoryIds());

        // 4. Lấy publisher
        Publisher publisher = publisherRepository.findById(req.getPublisherId())
                .orElseThrow(() -> new SystemException(ErrorCode.PUBLISHER_NOT_FOUND));


        // 5. Build Book
        Book book = Book.builder()
                .title(req.getTitle())
                .authors(req.getAuthors())
                .isbn(req.getIsbn())
                .price(req.getPrice())
                .quantity(req.getQuantity())
                .weight(req.getWeight().doubleValue())
                .description(req.getDescription())
                .publicationDate(req.getPublicationDate())
                .urlImage(imageUrl)
                .status(
                        req.getQuantity() > 0
                                ? BookStatus.ACTIVE
                                : BookStatus.OUT_OF_STOCK
                )
                .categories(categories)
                .publisher(publisher)
                .build();

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void updateBook(
            Long bookId,
            BookUpdateRequest req,
            MultipartFile image
    ) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new SystemException(ErrorCode.BOOK_NOT_FOUND));

        // update price & quantity
        book.setPrice(req.getPrice());
        book.setQuantity(req.getStock());
        book.setDescription(req.getDescription());

        // update status theo quantity
        book.setStatus(
                req.getStock() > 0
                        ? BookStatus.ACTIVE
                        : BookStatus.OUT_OF_STOCK
        );

        // update image nếu có
        if (image != null && !image.isEmpty()) {
            String imageUrl = s3Service.uploadFile(image);
            book.setUrlImage(imageUrl);
        }

        bookRepository.save(book);
    }

}
