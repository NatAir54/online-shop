package com.studying.onlineshop.web.filter;

import com.studying.onlineshop.service.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SecurityFilter implements Filter {
    private final SecurityService securityService;
    private final List<String> freePaths = List.of("/", "/signup", "/login", "logout");

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();
        for (String freePath : freePaths) {
            if(requestURI.endsWith(freePath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        if (securityService.isClientAuth(httpServletRequest)) {
            filterChain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }
}
