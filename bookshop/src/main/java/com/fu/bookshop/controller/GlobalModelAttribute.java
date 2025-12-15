package com.fu.bookshop.controller;


import com.fu.bookshop.service.home.HomeService;
import com.fu.bookshop.service.home.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttribute {

    private final ShopService shopService;
    private final HomeService homeService;

    @ModelAttribute
    public void addGlobalAttributes(Model model){
        model.addAttribute("categories", homeService.getAllCategories());
        model.addAttribute("publishers", homeService.getAllPublishers());
        model.addAttribute("shop", shopService.getDefaultShop());
    }
}
