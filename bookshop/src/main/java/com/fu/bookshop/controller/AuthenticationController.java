package com.fu.bookshop.controller;

import com.fu.bookshop.dto.AccountRegisterRequest;
import com.fu.bookshop.dto.OtpValidationRequest;
import com.fu.bookshop.enums.Gender;
import com.fu.bookshop.exception.AppException;
import com.fu.bookshop.service.AuthenticationService;
import com.fu.bookshop.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j(topic = "AUTHENTICATION-CONTROLLER")
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final OtpService otpService;

    /* =========================
       REGISTER
       ========================= */

    @GetMapping("/sign-up")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new AccountRegisterRequest());
        model.addAttribute("genders", Gender.values());
        return "signUp";
    }

    @PostMapping("/sign-up")
    public String register(
            @Valid @ModelAttribute("registerRequest") AccountRegisterRequest request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        // 1. Validate form (annotation)
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            return "signUp";
        }

        // 2. Validate confirm password (business-level)
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp");
            model.addAttribute("genders", Gender.values());
            return "signUp";
        }

        try {
            authenticationService.signUp(request);
            redirectAttributes.addFlashAttribute("email", request.getEmail());
            redirectAttributes.addFlashAttribute("name", request.getName());
            // redirect sang màn verify OTP
            return "redirect:/auth/verify-otp";

        } catch (AppException ex) {
            // business error: EMAIL_EXISTED, ACCOUNT_DEACTIVATED, ...
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("genders", Gender.values());
            return "signUp";
        }
    }

    /* =========================
       VERIFY OTP
       ========================= */

    @GetMapping("/verify-otp")
    public String showVerifyOtp(Model model) {

        // email tự động có trong model nếu redirect từ signUp
        if (!model.containsAttribute("email")) {
            // user truy cập trực tiếp → không hợp lệ
            return "redirect:/auth/sign-up";
        }

        OtpValidationRequest req = new OtpValidationRequest();
        req.setEmail((String) model.getAttribute("email"));

        model.addAttribute("otpRequest", req);
        return "otp";
    }


    @PostMapping("/verify-otp")
    public String verifyOtp(
            @Valid @ModelAttribute("otpRequest") OtpValidationRequest request,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            return "otp";
        }

        try {
            authenticationService.verifyAccountAndUpdateStatus(request);
            return "redirect:/auth/login";

        } catch (AppException ex) {
            model.addAttribute("error", ex.getMessage());
            return "otp";
        }
    }

    @PostMapping("/resend-otp")
    public String resendOtp(
            @RequestParam String email,
            @RequestParam String name,
            RedirectAttributes redirectAttributes

    ) {
        otpService.sendOtp(email, name);
        redirectAttributes.addFlashAttribute("message", "Đã gửi lại mã OTP");
        redirectAttributes.addFlashAttribute("email", email);
        redirectAttributes.addFlashAttribute("name", name);
        return "redirect:/auth/verify-otp";
    }
}
