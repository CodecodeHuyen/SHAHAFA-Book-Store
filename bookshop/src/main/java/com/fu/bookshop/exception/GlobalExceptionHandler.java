package com.fu.bookshop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SystemException.class)
    public String handleSystemException(SystemException ex, Model model) {
        logger.error("System error occurred: ", ex); // Sửa từ 'e' thành 'ex'
        model.addAttribute("errorCode", ex.getErrorCode().getCode());
        model.addAttribute("errorMessage", ex.getMessage());
        return "system-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleUnknownException(Exception ex, Model model) {
        logger.error("Unknown error occurred: ", ex); // Thêm logging
        model.addAttribute("errorCode", 9999);
        model.addAttribute("errorMessage", "Lỗi không xác định: " + ex.getMessage());
        return "system-error";
    }
}