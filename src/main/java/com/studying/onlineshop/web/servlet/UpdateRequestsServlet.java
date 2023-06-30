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

public class UpdateRequestsServlet extends HttpServlet {
    private GoodsService goodsService;

    public UpdateRequestsServlet(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Goods> goods = goodsService.findAll();
        PageGenerator instance = PageGenerator.instance();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);
        String page = instance.getPage("update.html", hashMap);

        response.getWriter().write(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Goods good = getUpdateRequest(request);
            goodsService.update(good, good.getId());
            response.sendRedirect("/goods/");
        } catch (Exception e) {
            String errorMessage = "Goods data is incorrect. Please try again!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);

            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("update.html", parameters);

            response.getWriter().write(page);
        }
    }

    private Goods getUpdateRequest(HttpServletRequest request) {
        Goods good = Goods.builder().
                id(Integer.parseInt(request.getParameter("id"))).
                name(request.getParameter("name")).
                price(Integer.parseInt(request.getParameter("price"))).
                build();

        return good;
    }

}
