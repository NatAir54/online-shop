package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageRequestsServlet extends HttpServlet {
    private GoodsService goodsService;

    public MainPageRequestsServlet(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("shop.html");
        response.getWriter().write(page);
    }
}
