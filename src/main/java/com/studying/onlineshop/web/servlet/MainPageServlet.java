package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.web.util.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("shop.html");
        response.getWriter().write(page);
    }
}
