package com.fu.bookshop.controller;

import com.fu.bookshop.dto.CartDTO;
import com.fu.bookshop.entity.Account;
import com.fu.bookshop.service.home.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String viewCart(@AuthenticationPrincipal Account account, Model model) {
        if (account == null) {
            return "redirect:/auth/login";
        }

        CartDTO cart = cartService.getCartByAccount(account);
        model.addAttribute("cart", cart);
        return "home/cart";
    }

    @PostMapping("/add")
    public String addToCart(
            @AuthenticationPrincipal Account account,
            @RequestParam Long bookId,
            @RequestParam(defaultValue = "1") Integer quantity,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        if (account == null) {
            return "redirect:/auth/login";
        }

        try {
            cartService.addToCart(account, bookId, quantity);
            redirectAttributes.addFlashAttribute("success", "Đã thêm sản phẩm vào giỏ hàng");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể thêm sản phẩm: " + e.getMessage());
        }

        // Lấy URL trang trước đó từ header "Referer"
        String referer = request.getHeader("Referer");

        // Nếu có referer, quay lại đúng trang đó (trang chi tiết sách, trang danh sách, ...)
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        }

        // Nếu không có, mặc định quay về trang chủ
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateQuantity(
            @AuthenticationPrincipal Account account,
            @RequestParam Long cartItemId,
            @RequestParam Integer quantity,
            RedirectAttributes redirectAttributes
    ) {
        if (account == null) {
            return "redirect:/auth/login";
        }

        try {
            cartService.updateCartItemQuantity(account, cartItemId, quantity);
            redirectAttributes.addFlashAttribute("success", "Đã cập nhật số lượng");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể cập nhật: " + e.getMessage());
        }

        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(
            @AuthenticationPrincipal Account account,
            @RequestParam Long cartItemId,
            RedirectAttributes redirectAttributes
    ) {
        if (account == null) {
            return "redirect:/auth/login";
        }

        try {
            cartService.removeCartItem(account, cartItemId);
            redirectAttributes.addFlashAttribute("success", "Đã xóa sản phẩm khỏi giỏ hàng");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa: " + e.getMessage());
        }

        return "redirect:/cart";
    }
}

