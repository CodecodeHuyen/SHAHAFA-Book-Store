package com.fu.bookshop.controller.user;

import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.service.AuthenticationService;
import com.fu.bookshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserService userService;

    @GetMapping
    public String profile (
            @RequestParam(name = "status", required = false)OrderStatus status,
            Principal principal,
            Model model
            ){
        String email = principal.getName();


        UserProfileDTO user = userService.getUserProfileByEmail(email);

        List<OrderHistoryDTO> orders;

        if(status == null){
            orders = userService.getOrderHistory(email);
        }else {
            orders = userService.getOrderHistoryByStatus(email, status);
        }

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("currentStatus", status);

        return "user/profile";
    }
}
