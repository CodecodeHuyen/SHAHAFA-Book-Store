package com.fu.bookshop.service;

import com.fu.bookshop.dto.BookCardDTO;
import com.fu.bookshop.dto.BookCreateRequest;
import com.fu.bookshop.dto.BookDetailDTO;
import com.fu.bookshop.dto.BookListDTO;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {

    BookDetailDTO getBookDetail(Long bookId);

    List<BookCardDTO> getRelatedBooks(Long bookId);
    Page<BookListDTO> getFilteredBooks(
            String bookStatus,
            Long publisherId,
            List<Long> categoryIds,
            String keyword,
            int page,
            int size
    );

    void createBook(
            BookCreateRequest req,
            MultipartFile image
    ) ;
}
