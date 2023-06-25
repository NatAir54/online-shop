package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GoodsRequestsServlet extends HttpServlet {
    private GoodsService goodsService;

    public GoodsRequestsServlet(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Goods> goods = goodsService.findAll();
        PageGenerator instance = PageGenerator.instance();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);
        String page = instance.getPage("goods_list.html", hashMap);

        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    }
}
