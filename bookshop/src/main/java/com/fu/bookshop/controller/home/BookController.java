package com.fu.bookshop.controller.home;

import com.fu.bookshop.dto.home.BookCardDTO;
import com.fu.bookshop.dto.home.BookDetailDTO;
import com.fu.bookshop.service.home.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public String bookDetail(
            @PathVariable Long id,
            Model model
    ){
        BookDetailDTO book = bookService.getBookDetail(id);
        List<BookCardDTO> relatedBooks = bookService.getRelatedBooks(id);

        model.addAttribute("book", book);
        model.addAttribute("relatedBooks", relatedBooks);

        return "home/book-detail";
    }
}
