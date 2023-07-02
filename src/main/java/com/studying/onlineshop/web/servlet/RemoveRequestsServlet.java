package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveRequestsServlet extends HttpServlet {
    private final GoodsService goodsService;
    private final PageGenerator pageGenerator = PageGenerator.instance();
    private final List<String> userTokens;

    public RemoveRequestsServlet(GoodsService goodsService, List<String> userTokens) {
        this.goodsService = goodsService;
        this.userTokens = userTokens;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isAuth = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (userTokens.contains(cookie.getValue())) {
                        isAuth = true;
                    }
                    break;
                }
            }
        }

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
