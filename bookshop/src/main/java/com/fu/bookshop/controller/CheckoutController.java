package com.fu.bookshop.controller;


import com.fu.bookshop.dto.CartItemDTO;
import com.fu.bookshop.dto.CreateOrderRequest;
import com.fu.bookshop.dto.OrderDTO;
import com.fu.bookshop.dto.OrderItemDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.entity.Book;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.repository.BookRepository;
import com.fu.bookshop.service.OrderService;
import com.fu.bookshop.service.PaymentService;
import com.fu.bookshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderService orderService;
    private final UserService userService;
    private final PaymentService paymentService;
    private final BookRepository bookRepository;

    @PostMapping("/buy-now")
    public String buyNow(@RequestParam("bookId") long bookId, @RequestParam("quantity") int quantity, Principal principal, Model model) {
        String email = principal.getName();
        UserProfileDTO user = userService.getByEmail(email);

        OrderDTO orderDTO = orderService.createOrderFromBuyNow(user.getId(), bookId, quantity);

        model.addAttribute("user", user);
        model.addAttribute("order", orderDTO);

        return "checkout/checkout";
    }

    @PostMapping("/process-payment")
    public String processPayment(
            @RequestParam("orderCode") long orderCode,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description
    ) {
        try {
            String paymentUrl = paymentService.createPaymentLink(orderCode, amount, description);
            return "redirect:" + paymentUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

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

        return "checkout/checkout";
    }
}
