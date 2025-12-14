package com.fu.bookshop.controller.home;

import com.fu.bookshop.dto.BookCardDTO;
import com.fu.bookshop.entity.Genre;
import com.fu.bookshop.entity.Publisher;
import com.fu.bookshop.service.home.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping("/")
    public String home(@RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "10") int size,
                       @RequestParam(name = "keyword", required = false) String keyword,
                       @RequestParam(name = "category", required = false)List<Long> categoryIds,
                       @RequestParam(name = "publisher", required = false) Long publisherId,
                       Model model){
        Page<BookCardDTO> bookPage;

        if(keyword != null && !keyword.isBlank()){
            bookPage = homeService.searchBooks(keyword, page, size);

        } else if (categoryIds != null && !categoryIds.isEmpty() && publisherId != null) {

            bookPage = homeService.getBooksByCategoriesAndPublisher(categoryIds, publisherId, page, size);
        }else if(categoryIds != null && !categoryIds.isEmpty()){
            if(categoryIds.size() == 1){
                bookPage = homeService.getBooksByCategory(categoryIds.get(0), page, size);

            }else {
                bookPage = homeService.getBooksByCategories(categoryIds, page, size);
            }
        } else if (publisherId != null) {
            bookPage = homeService.getBooksByPublisher(publisherId, page, size);

        }else {
            bookPage = homeService.getActiveBooks(page, size);
        }

        List<BookCardDTO> bestSellerBooks = homeService.getBestSellerBooks();
        model.addAttribute("bestSellerBooks", bestSellerBooks);

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalElements", bookPage.getTotalElements());
        model.addAttribute("pageSize", size);

        List<Genre> categories = homeService.getAllCategories();
        List<Publisher> publishers = homeService.getAllPublishers();
        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);

        model.addAttribute("selectedCategories", categoryIds);
        model.addAttribute("selectedPublisher", publisherId);
        model.addAttribute("keyword", keyword);
        return "home/index";
    }


    @GetMapping("/book/{id}")
    public String bookDetail(@PathVariable Long id, Model model){
        BookCardDTO book = homeService.getBookDetail(id);
        model.addAttribute("book", book);

        return "home/book-detail";
    }

}
