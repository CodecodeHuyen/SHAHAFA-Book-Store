package com.fu.bookshop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority())
                .collect(Collectors.toSet());

        if (roles.contains("ROLE_MANAGER")) {
            getRedirectStrategy().sendRedirect(request, response, "/manager");
        } else if (roles.contains("ROLE_ADMIN")) {
            getRedirectStrategy().sendRedirect(request, response, "/admin/account");
        }
        else {
            // Nếu là Customer hoặc các role khác:
            // SavedRequestAware sẽ tự tìm trang cũ người dùng đang xem (ví dụ /cart)

//            super.setDefaultTargetUrl("/home");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}