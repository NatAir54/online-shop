package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.web.util.PageGenerator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final List<String> userTokens;

    public LoginServlet(List<String> userTokens) {
        this.userTokens = userTokens;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("login.html");
        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String userToken = UUID.randomUUID().toString();
        userTokens.add(userToken);
        Cookie cookie = new Cookie("user-token", userToken);
        response.addCookie(cookie);

        response.sendRedirect("/");
    }
}
