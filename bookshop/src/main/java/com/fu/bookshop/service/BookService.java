package com.fu.bookshop.service;

import com.fu.bookshop.dto.*;
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

    @Transactional
    void updateBook(
            Long bookId,
            BookUpdateRequest req,
            MultipartFile image
    );
}
