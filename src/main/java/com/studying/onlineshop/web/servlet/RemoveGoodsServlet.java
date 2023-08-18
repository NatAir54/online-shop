package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveGoodsServlet extends HttpServlet {
    private GoodsService GOODS_SERVICE;
    private final PageGenerator PAGE_GENERATOR = PageGenerator.instance();

    public RemoveGoodsServlet() {
    }

    public RemoveGoodsServlet(GoodsService goodsService) {
        this.GOODS_SERVICE = goodsService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Goods> goods = GOODS_SERVICE.findAll();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);
        String page = PAGE_GENERATOR.getPage("remove.html", hashMap);
        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int idForRemove = Integer.parseInt(request.getParameter("id"));
            GOODS_SERVICE.remove(idForRemove);
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods id is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = PAGE_GENERATOR.getPage("remove.html", parameters);
            response.getWriter().write(page);
        }
    }
}
