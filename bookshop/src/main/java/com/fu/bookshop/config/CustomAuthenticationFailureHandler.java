package com.fu.bookshop.config;

import com.fu.bookshop.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {

        String errorMessage = "Mật khẩu không đúng";

        if (exception.getCause() instanceof BusinessException be) {
            errorMessage = be.getMessage();
        }

        // redirect kèm message
        response.sendRedirect(
                "/auth/login?error=" +
                        URLEncoder.encode(errorMessage, StandardCharsets.UTF_8)
        );
    }
}
