package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveRequestsServlet extends HttpServlet {
    private GoodsService goodsService;

    public RemoveRequestsServlet(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Goods> goods = goodsService.findAll();
        PageGenerator instance = PageGenerator.instance();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);
        String page = instance.getPage("remove.html", hashMap);

        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int idForRemove = Integer.parseInt(request.getParameter("id"));
            goodsService.remove(idForRemove);
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods id is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);

            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("remove.html", parameters);

            response.getWriter().write(page);
        }
    }
}
