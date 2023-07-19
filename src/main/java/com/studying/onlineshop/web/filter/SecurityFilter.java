package com.studying.onlineshop.web.filter;

import com.studying.onlineshop.service.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityFilter implements Filter {
    private final SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getRequestURI().endsWith("/login")) {
            filterChain.doFilter(request, response);
        } else {
            if (securityService.isClientAuth(httpServletRequest)) {
                filterChain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect("/login");
            }
        }
    }
}
