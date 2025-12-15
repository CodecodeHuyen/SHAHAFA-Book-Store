package com.fu.bookshop.controller;


import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.service.home.HomeService;
import com.fu.bookshop.service.home.ShopService;
import com.fu.bookshop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttribute {

    private final ShopService shopService;
    private final HomeService homeService;
    private final UserService userService;


    @ModelAttribute
    public void addGlobalAttributes(Model model){
        model.addAttribute("categories", homeService.getAllCategories());
        model.addAttribute("publishers", homeService.getAllPublishers());
        model.addAttribute("shop", shopService.getDefaultShop());
    }

    @ModelAttribute
    public void addCurrentUser(Model model, Authentication authentication){
        if(authentication == null ||
            !authentication.isAuthenticated() ||
            "anonymousUser".equals(authentication.getPrincipal())){
            return;
        }

        String email = authentication.getName();

        UserProfileDTO currentUser = userService.getUserProfileByEmail(email);

        model.addAttribute("currentUser", currentUser);
    }
}
