package com.fu.bookshop.service.home;

import com.fu.bookshop.dto.home.BookCardDTO;
import com.fu.bookshop.dto.home.BookDetailDTO;

import java.util.List;

public interface BookService {

    BookDetailDTO getBookDetail(Long bookId);

    List<BookCardDTO> getRelatedBooks(Long bookId);
}
