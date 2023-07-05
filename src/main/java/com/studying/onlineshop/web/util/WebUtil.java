package com.studying.onlineshop.web.util;

import com.studying.onlineshop.entity.Client;
import com.studying.onlineshop.entity.Goods;
import jakarta.servlet.http.HttpServletRequest;


public class WebUtil {
    public static Client getClient(HttpServletRequest request) {
        Client client = Client.builder().
                email(request.getParameter("email")).
                password(request.getParameter("password")).
                build();
        return client;
    }

    public static Goods getGoods(HttpServletRequest request) {
        Goods good = Goods.builder().
                name(request.getParameter("name")).
                price(Integer.parseInt(request.getParameter("price"))).
                build();
        return good;
    }
}
