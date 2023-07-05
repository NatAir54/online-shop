package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.service.SecurityService;
import com.studying.onlineshop.web.util.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveGoodsServlet extends HttpServlet {
    private final GoodsService goodsService;
    private final SecurityService securityService;
    private final PageGenerator pageGenerator = PageGenerator.instance();

    public RemoveGoodsServlet(GoodsService goodsService, SecurityService securityService) {
        this.goodsService = goodsService;
        this.securityService = securityService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isAuth = securityService.isClientAuth(request);
        if (isAuth) {
            List<Goods> goods = goodsService.findAll();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("goods", goods);
            String page = pageGenerator.getPage("remove.html", hashMap);
            response.getWriter().write(page);
        } else {
            response.sendRedirect("/login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int idForRemove = Integer.parseInt(request.getParameter("id"));
            goodsService.remove(idForRemove);
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods id is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = pageGenerator.getPage("remove.html", parameters);
            response.getWriter().write(page);
        }
    }
}
