package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AddRequestServlet extends HttpServlet {
    private GoodsService goodsService;

    public AddRequestServlet(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("add.html");
        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Goods good = getGoodsRequested(request);
            goodsService.add(good);
            response.sendRedirect("/goods/");
        } catch (SQLException e) {
            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("add.html", new HashMap<>());
            response.getWriter().write(page);
            response.getWriter().write("Goods data is incorrect. Please try again!");
        }
    }

    private Goods getGoodsRequested(HttpServletRequest request) {
        Goods good = Goods.builder().
                name(request.getParameter("name")).
                price(Integer.parseInt(request.getParameter("price"))).
                build();

        return good;
    }
}
