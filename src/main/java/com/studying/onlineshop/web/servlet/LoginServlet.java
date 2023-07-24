package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Client;
import com.studying.onlineshop.service.ClientService;
import com.studying.onlineshop.service.SecurityService;
import com.studying.onlineshop.web.util.PageGenerator;
import com.studying.onlineshop.web.util.WebUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class LoginServlet extends HttpServlet {
    private final ClientService clientService;
    private final SecurityService securityService;
    private final PageGenerator pageGenerator = PageGenerator.instance();


    public LoginServlet(ClientService clientService, SecurityService securityService) {
        this.clientService = clientService;
        this.securityService = securityService;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = pageGenerator.getPage("login.html");
        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Client client = WebUtil.getClient(request);
        if (clientService.findByEmail(client.getEmail()) != null) {
            String userToken = securityService.login(client);
            if (userToken != null) {
                Cookie cookie = new Cookie("user-token", userToken);
                response.addCookie(cookie);
                response.sendRedirect("/");
            } else {
                String errorMessage = "Password is incorrect!";
                Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
                String page = pageGenerator.getPage("login.html", parameters);
                response.getWriter().write(page);
            }
        } else {
            String errorMessage = "Email address is incorrect or not registered yet. Sign up please!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = pageGenerator.getPage("login.html", parameters);
            response.getWriter().write(page);
        }
    }
}
