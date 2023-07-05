package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.service.SecurityService;
import com.studying.onlineshop.web.util.PageGenerator;
import com.studying.onlineshop.web.util.WebUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AddGoodsServlet extends HttpServlet {
    private final GoodsService goodsService;
    private final SecurityService securityService;
    private final PageGenerator pageGenerator = PageGenerator.instance();

    public AddGoodsServlet(GoodsService goodsService, SecurityService securityService) {
        this.goodsService = goodsService;
        this.securityService = securityService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isAuth = securityService.isClientAuth(request);
        if (isAuth) {
            String page = pageGenerator.getPage("add.html");
            response.getWriter().write(page);
        } else {
            response.sendRedirect("/login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Goods good = WebUtil.getGoods(request);
            goodsService.add(good);
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods data is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = pageGenerator.getPage("add.html", parameters);
            response.getWriter().write(page);
        }
    }
}
