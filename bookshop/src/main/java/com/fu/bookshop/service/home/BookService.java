package com.fu.bookshop.service.home;

import com.fu.bookshop.dto.BookCardDTO;
import com.fu.bookshop.dto.BookDetailDTO;

import java.util.List;

public interface BookService {

    BookDetailDTO getBookDetail(Long bookId);

    List<BookCardDTO> getRelatedBooks(Long bookId);
}
