package com.studying.onlineshop.servlets;

import com.studying.onlineshop.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AllRequestsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("shop.html", new HashMap<>());

        response.getWriter().println(page);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    }
}
