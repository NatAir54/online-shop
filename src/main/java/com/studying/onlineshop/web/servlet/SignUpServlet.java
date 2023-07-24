package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Client;
import com.studying.onlineshop.service.ClientService;
import com.studying.onlineshop.service.SecurityService;
import com.studying.onlineshop.web.util.PageGenerator;
import com.studying.onlineshop.web.util.WebUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SignUpServlet extends HttpServlet {
    private final SecurityService SECURITY_SERVICE;
    private final ClientService CLIENT_SERVICE;
    private final PageGenerator PAGE_GENERATOR = PageGenerator.instance();

    public SignUpServlet(SecurityService securityService, ClientService clientService) {
        this.SECURITY_SERVICE = securityService;
        this.CLIENT_SERVICE = clientService;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PAGE_GENERATOR.getPage("signup.html");
        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Client client = WebUtil.getClient(request);
            if (CLIENT_SERVICE.findByEmail(client.getEmail()) == null) {
                SECURITY_SERVICE.signup(client);
                response.sendRedirect("/");
            } else {
                String errorMessage = "Email is registered already. Login please!";
                Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
                String page = PAGE_GENERATOR.getPage("signup.html", parameters);
                response.getWriter().write(page);
            }
        } catch (Exception e) {
            String errorMessage = "Data is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = PAGE_GENERATOR.getPage("signup.html", parameters);
            response.getWriter().write(page);
        }
    }
}
