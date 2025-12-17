package com.fu.bookshop.controller;

import com.fu.bookshop.dto.*;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.service.BookService;
import com.fu.bookshop.service.CategoryService;
import com.fu.bookshop.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;


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

    @GetMapping("detail/{id}")
    public String bookDetail2(
            @PathVariable Long id,
            Model model
    ) {
        BookDetailDTO book = bookService.getBookDetail(id);

        model.addAttribute("book", book);
        model.addAttribute("bookUpdate", new BookUpdateRequest(
                book.getPrice(),
                book.getStock(),
                book.getDescription()
        ));

        return "manager/book-detail";
    }


    @GetMapping
    public String bookManagement(
            @RequestParam(required = false) String bookStatus,
            @RequestParam(required = false) Long publisherId,
            @RequestParam(required = false) List<Long> categoryIds,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {

        Page<BookListDTO> bookPage =
                bookService.getFilteredBooks(
                        bookStatus,
                        publisherId,
                        categoryIds,
                        keyword,
                        page,
                        size
                );

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalElements", bookPage.getTotalElements());

        // filter data
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("publishers", publisherService.getAll());

        // giữ lại giá trị filter
        model.addAttribute("bookStatus", bookStatus);
        model.addAttribute("publisherId", publisherId);
        model.addAttribute("categoryIds", categoryIds);
        model.addAttribute("keyword", keyword);

        return "manager/book-management";
    }

    @PostMapping("/create")
    public String createBook(
            @Valid @ModelAttribute("book") BookCreateRequest request,
            BindingResult bindingResult,
            @RequestParam(value = "image") MultipartFile image,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("publishers", publisherService.getAll());
            return "manager/book-create";
        }

        try {
            bookService.createBook(request, image);
        } catch (BusinessException e) {
            bindingResult.reject("error", e.getMessage());
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("publishers", publisherService.getAll());
            return "manager/book-create";
        }

        return "redirect:/books";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        model.addAttribute("book", new BookCreateRequest());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("publishers", publisherService.getAll());

        return "manager/book-create";
    }

    @PostMapping("/{id}/edit")
    public String updateBook(
            @PathVariable Long id,
            @Valid @ModelAttribute("bookUpdate") BookUpdateRequest request,
            BindingResult result,
            @RequestParam(required = false) MultipartFile image,
            Model model
    ) {
        if (result.hasErrors()) {
            BookDetailDTO book = bookService.getBookDetail(id);
            model.addAttribute("book", book);
            return "manager/book-detail";
        }

        bookService.updateBook(id, request, image);

        return "redirect:/books/detail/" + id;
    }




}
