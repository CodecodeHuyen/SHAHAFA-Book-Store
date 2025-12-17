package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.BookCardDTO;
import com.fu.bookshop.entity.Book;
import com.fu.bookshop.entity.Category;
import com.fu.bookshop.entity.Publisher;
import com.fu.bookshop.enums.BookStatus;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.repository.BookRepository;
import com.fu.bookshop.repository.CategoryRepository;
import com.fu.bookshop.repository.PublisherRepository;
import com.fu.bookshop.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;


    private BookCardDTO mapToCardDTO(Book book) {
        if (book == null) {
            return null;
        }

        return BookCardDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .image(book.getUrlImage())
                .price(book.getPrice())
                .build();
    }
//pass
    @Override
    public Page<BookCardDTO> getActiveBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findByStatus(BookStatus.ACTIVE, pageable)
                .map(this::mapToCardDTO);
    }

//pass
    @Override
    public Page<BookCardDTO> searchBooks(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (keyword == null || keyword.isBlank()) {
            return getActiveBooks(page, size);
        }

        return bookRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                .map(this::mapToCardDTO);
    }

    @Override
    public Page<BookCardDTO> getBooksByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return bookRepository.findByCategories_Id(categoryId, pageable)
                .map(this::mapToCardDTO);
    }

    @Override
    public Page<BookCardDTO> getBooksByCategories(List<Long> categoryIds, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return bookRepository.findDistinctByCategories_IdIn(categoryIds, pageable)
                .map(this::mapToCardDTO);
    }
//pass
    @Override
    public Page<BookCardDTO> getBooksByPublisher(Long publisherId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return bookRepository.findByPublisher_Id(publisherId, pageable)
                .map(this::mapToCardDTO);
    }
//pass

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
//pass

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Page<BookCardDTO> getBooksByCategoriesAndPublisher(List<Long> categoryIds, Long publisherId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return bookRepository
                .findDistinctByCategories_IdInAndPublisher_Id(categoryIds, publisherId, pageable)
                .map(this::mapToCardDTO);
    }

    @Override
    public List<BookCardDTO> getBestSellerBooks() {
        List<Book> books = bookRepository.findBestSellerBooks(OrderStatus.COMPLETE,
                PageRequest.of(0, 5));


        return books.stream()
                .map(this::mapToCardDTO)
                .toList();
    }
}
