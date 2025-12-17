package com.fu.bookshop.controller;


import com.fu.bookshop.dto.BookCardDTO;
import com.fu.bookshop.entity.Category;
import com.fu.bookshop.entity.Publisher;
import com.fu.bookshop.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;
    
    @GetMapping()
    public String home(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "category", required = false) List<Long> categoryIds,
            @RequestParam(name = "publisher", required = false) Long publisherId,
            Model model
    ) {

        Page<BookCardDTO> bookPage = getBookPage(keyword, categoryIds, publisherId, page, size);


        List<BookCardDTO> bestSellerBooks =
                Optional.ofNullable(homeService.getBestSellerBooks())
                        .orElse(Collections.emptyList());

        List<Category> categories =
                Optional.ofNullable(homeService.getAllCategories())
                        .orElse(Collections.emptyList());

        if (bookPage == null) {                 // ✅ FIX 2
            bookPage = Page.empty();
        }


        model.addAttribute("bestSellerBooks", bestSellerBooks);

       List<Publisher> publishers =
                Optional.ofNullable(homeService.getAllPublishers())
                        .orElse(Collections.emptyList());

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalElements", bookPage.getTotalElements());
        model.addAttribute("pageSize", size);


        model.addAttribute("bestSellerBooks", bestSellerBooks);
        model.addAttribute("hasBestSeller", !bestSellerBooks.isEmpty());

        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);

        model.addAttribute("selectedCategories",
                categoryIds == null ? Collections.emptyList() : categoryIds);
        model.addAttribute("selectedPublisher", publisherId);
        model.addAttribute("keyword", keyword);

        return "home/index";
    }

    private Page<BookCardDTO> getBookPage(
            String keyword,
            List<Long> categoryIds,
            Long publisherId,
            int page,
            int size
    ) {

        if (keyword != null && !keyword.isBlank()) {
            return homeService.searchBooks(keyword, page, size);
        }

        if (categoryIds != null && !categoryIds.isEmpty() && publisherId != null) {
            return homeService.getBooksByCategoriesAndPublisher(categoryIds, publisherId, page, size);
        }

        if (categoryIds != null && !categoryIds.isEmpty()) {
            return categoryIds.size() == 1
                    ? homeService.getBooksByCategory(categoryIds.get(0), page, size)
                    : homeService.getBooksByCategories(categoryIds, page, size);
        }

        if (publisherId != null) {
            return homeService.getBooksByPublisher(publisherId, page, size);
        }

        return homeService.getActiveBooks(page, size);
    }


}
