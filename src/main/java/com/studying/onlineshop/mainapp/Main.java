package com.studying.onlineshop.mainapp;

import com.studying.onlineshop.dao.jdbc.GoodsDao;
import com.studying.onlineshop.dao.jdbc.JdbcGoodsDao;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.servlet.AddRequestServlet;
import com.studying.onlineshop.web.servlet.GoodsRequestsServlet;
import com.studying.onlineshop.web.servlet.ShopRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;



public class Main {
    public static void main(String[] args) throws Exception {
        GoodsDao jdbcGoods = new JdbcGoodsDao();
        GoodsService goodsService = new GoodsService(jdbcGoods);

        ShopRequestServlet shopRequestServlet = new ShopRequestServlet(goodsService);
        GoodsRequestsServlet goodsRequestsServlet = new GoodsRequestsServlet(goodsService);
        AddRequestServlet addRequestServlet = new AddRequestServlet(goodsService);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(shopRequestServlet), "");
        context.addServlet(new ServletHolder(goodsRequestsServlet), "/goods/");
        context.addServlet(new ServletHolder(addRequestServlet), "/goods/add");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}