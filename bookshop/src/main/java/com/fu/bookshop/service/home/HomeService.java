package com.fu.bookshop.service.home;

import com.fu.bookshop.dto.BookCardDTO;
import com.fu.bookshop.entity.Book;
import com.fu.bookshop.entity.Category;
import com.fu.bookshop.entity.Publisher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HomeService {

    Page<BookCardDTO> getActiveBooks(int page, int size);

    Page<BookCardDTO> searchBooks(String keyword, int page, int size);

    Page<BookCardDTO> getBooksByCategory(Long categoryId, int page, int size);

    Page<BookCardDTO> getBooksByCategories(List<Long> categoryIds, int page, int size);

    Page<BookCardDTO> getBooksByPublisher(Long publisherId, int page, int size);

    BookCardDTO getBookDetail(Long id);

    List<Category> getAllCategories();

    List<Publisher> getAllPublishers();

    Page<BookCardDTO> getBooksByCategoriesAndPublisher(
            List<Long> categoryIds,
            Long publisherId,
            int page,
            int size
    );

    public List<BookCardDTO> getBestSellerBooks();

}
