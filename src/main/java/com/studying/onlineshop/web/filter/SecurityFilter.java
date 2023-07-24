package com.studying.onlineshop.web.filter;

import com.studying.onlineshop.service.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SecurityFilter implements Filter {
    private final SecurityService SECURITY_SERVICE;
    private final List<String> FREE_PATHS = List.of("/", "/signup", "/login", "logout");

    public SecurityFilter(SecurityService securityService) {
        this.SECURITY_SERVICE = securityService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();
        for (String freePath : FREE_PATHS) {
            if(requestURI.endsWith(freePath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        Cookie[] cookies = httpServletRequest.getCookies();

        if (SECURITY_SERVICE.isClientAuth(cookies)) {
            filterChain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }
}
