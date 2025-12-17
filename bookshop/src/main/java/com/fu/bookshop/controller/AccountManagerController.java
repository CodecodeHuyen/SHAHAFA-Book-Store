package com.fu.bookshop.controller;

import com.fu.bookshop.dto.AccountCreateRequest;
import com.fu.bookshop.enums.AccountStatus;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AccountManagerController {

    private final AccountService accountService;

    @GetMapping("/admin/account")
    public String listAccounts(Model model,
                               @RequestParam(required = false) AccountStatus status,
                               @RequestParam(required = false, defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<?> accounts = accountService.getAccounts(status, keyword, pageable);

        model.addAttribute("accounts", accounts);
        model.addAttribute("accountStatuses", AccountStatus.values());
        model.addAttribute("status", status != null ? status.name() : "");
        model.addAttribute("keyword", keyword);

        model.addAttribute("accountCreateRequest", new AccountCreateRequest());

        return "admin/account-list";
    }

    @PostMapping("/admin/account")
    public String createAccount(
            @Valid @ModelAttribute("accountCreateRequest") AccountCreateRequest request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        // 1. Lỗi validate (bao gồm @ValidDob cho ngày sinh)
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
            Page<?> accounts = accountService.getAccounts(null, "", pageable);

            model.addAttribute("accounts", accounts);
            model.addAttribute("accountStatuses", AccountStatus.values());
            model.addAttribute("status", "");
            model.addAttribute("keyword", "");
            // request + bindingResult đã tự được đưa vào model với tên "accountCreateRequest"

            return "admin/account-list";
        }

        // 2. Lỗi nghiệp vụ (email trùng, role không hợp lệ, ...)
        try {
            accountService.createAccount(request);
            redirectAttributes.addFlashAttribute("success", "Tạo tài khoản mới thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/admin/account";
    }

    @PostMapping("/admin/account/delete")
    public String deleteAccount(@RequestParam("id") Long accountId,
                                RedirectAttributes redirectAttributes) {
        try {
            accountService.deleteAccount(accountId);
            redirectAttributes.addFlashAttribute("success", "Xóa tài khoản thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/admin/account";
    }

    @PostMapping("/admin/account/toggle-status")
    public String toggleAccountStatus(@RequestParam("id") Long accountId,
                                      RedirectAttributes redirectAttributes) {
        try {
            accountService.toggleAccountStatus(accountId);
            redirectAttributes.addFlashAttribute("success", "Cập nhật trạng thái tài khoản thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/admin/account";
    }
}

