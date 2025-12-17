package com.fu.bookshop.controller;


import com.fu.bookshop.dto.OrderDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.service.OrderService;
import com.fu.bookshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/create")
    public String createCheckout(
            @RequestParam("cartItemIds") String cartItemIds,
            Principal principal,
            Model model
    ) {
        String email = principal.getName();
        UserProfileDTO user = userService.getByEmail(email);

        List<Long> ids = Arrays.stream(cartItemIds.split(","))
                .filter(s -> !s.isBlank())
                .map(Long::valueOf)
                .toList();

        OrderDTO order = orderService.createCheckout(email, ids);

        model.addAttribute("user", user);
        model.addAttribute("order", order);

        return "checkout/checkout"; // checkout.html
    }
}
