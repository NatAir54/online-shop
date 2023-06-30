package com.studying.onlineshop.web.servlet;

import com.studying.onlineshop.entity.Goods;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.util.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AddRequestsServlet extends HttpServlet {
    private GoodsService goodsService;

    public AddRequestsServlet(GoodsService goodsService) {
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
        } catch (Exception e) {
            String errorMessage = "Goods data is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);

            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("add.html", parameters);

            response.getWriter().write(page);
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
