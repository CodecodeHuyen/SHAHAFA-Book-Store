package com.fu.bookshop.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public String handleSystemException(SystemException ex, Model model) {
        model.addAttribute("errorCode", ex.getErrorCode().getCode());
        model.addAttribute("errorMessage", ex.getMessage());
        return "system-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleUnknownException(Exception ex, Model model) {
        model.addAttribute("errorCode", 9999);
        model.addAttribute("errorMessage", "Lỗi không xác định");
        return "system-error";
    }
}
