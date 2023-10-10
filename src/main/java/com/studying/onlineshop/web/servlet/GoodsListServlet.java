package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class GoodsListServlet extends HttpServlet {
    @Autowired
    private GoodsService GOODS_SERVICE;

    public GoodsListServlet() {
    }

    public GoodsListServlet(GoodsService goodsService) {
        this.GOODS_SERVICE = goodsService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Goods> goods = GOODS_SERVICE.findAll();
        PageGenerator instance = PageGenerator.instance();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);
        String page = instance.getPage("goods_list.html", hashMap);

        response.getWriter().write(page);
    }
}
