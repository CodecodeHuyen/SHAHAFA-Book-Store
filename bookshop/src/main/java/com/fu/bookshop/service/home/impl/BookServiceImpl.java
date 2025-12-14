package com.fu.bookshop.service.home.impl;

import com.fu.bookshop.dto.BookCardDTO;
import com.fu.bookshop.dto.BookDetailDTO;
import com.fu.bookshop.entity.Book;
import com.fu.bookshop.entity.Genre;
import com.fu.bookshop.enums.BookStatus;
import com.fu.bookshop.repository.BookRepository;
import com.fu.bookshop.service.home.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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
                .price(book.getPrice())
                .oldPrice(null)
                .salePercent(null)
                .description(book.getDescription())
                .authors(book.getAuthors())
                .publisherName(book.getPublisher().getName())
                .publicationDate(book.getPublicationDate() != null ? book.getPublicationDate() : null)
                .language("Tieng Viet")
                .pages(null)
                .weight(
                        book.getWeight() != null
                                ? book.getWeight().intValue()
                                : null
                )
                .size(null)
                .build();
    }

    @Override
    public List<BookCardDTO> getRelatedBooks(Long bookId) {
        Book book = bookRepository.findDetailById(bookId).orElseThrow(() -> new RuntimeException());
        List<Long> categoryIds = book.getCategories()
                .stream()
                .map(Genre::getId)
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

}
