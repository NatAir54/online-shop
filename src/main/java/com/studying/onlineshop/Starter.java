package com.studying.onlineshop;

import com.studying.onlineshop.dao.jdbc.GoodsDao;
import com.studying.onlineshop.dao.jdbc.JdbcGoodsDao;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;



public class Starter {
    public static void main(String[] args) throws Exception {
        GoodsDao jdbcGoods = new JdbcGoodsDao();
        GoodsService goodsService = new GoodsService(jdbcGoods);

        MainPageRequestsServlet shopRequestServlet = new MainPageRequestsServlet(goodsService);
        GoodsRequestsServlet goodsRequestsServlet = new GoodsRequestsServlet(goodsService);
        AddRequestsServlet addRequestServlet = new AddRequestsServlet(goodsService);
        UpdateRequestsServlet updateRequestsServlet = new UpdateRequestsServlet(goodsService);
        RemoveRequestsServlet removeRequestsServlet = new RemoveRequestsServlet(goodsService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(shopRequestServlet), "");
        context.addServlet(new ServletHolder(goodsRequestsServlet), "/goods/");
        context.addServlet(new ServletHolder(addRequestServlet), "/goods/add");
        context.addServlet(new ServletHolder(updateRequestsServlet), "/goods/update");
        context.addServlet(new ServletHolder(removeRequestsServlet), "/goods/remove");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}