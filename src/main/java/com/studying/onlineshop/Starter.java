package com.studying.onlineshop;

import com.studying.onlineshop.dao.ClientDao;
import com.studying.onlineshop.dao.GoodsDao;
import com.studying.onlineshop.dao.jdbc.JdbcClientDao;
import com.studying.onlineshop.dao.jdbc.JdbcGoodsDao;
import com.studying.onlineshop.service.ClientService;
import com.studying.onlineshop.service.GoodsService;
import com.studying.onlineshop.service.SecurityService;
import com.studying.onlineshop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.List;


public class Starter {
    public static void main(String[] args) throws Exception {
        GoodsDao jdbcGoods = new JdbcGoodsDao();
        ClientDao clientDao = new JdbcClientDao();
        GoodsService goodsService = new GoodsService(jdbcGoods);
        ClientService clientService = new ClientService(clientDao);

        List<String> clientTokens = new ArrayList<>();
        SecurityService securityService =  new SecurityService(clientService, clientTokens);

        MainPageServlet mainPageServlet = new MainPageServlet(goodsService);
        GoodsListServlet goodsRequestsServlet = new GoodsListServlet(goodsService);
        SignUpServlet signUpServlet = new SignUpServlet(securityService, clientService);
        LoginServlet loginRequestsServlet = new LoginServlet(clientService, securityService);
        AddGoodsServlet addRequestServlet = new AddGoodsServlet(goodsService, securityService);
        UpdateGoodsServlet updateRequestsServlet = new UpdateGoodsServlet(goodsService, securityService);
        RemoveGoodsServlet removeRequestsServlet = new RemoveGoodsServlet(goodsService, securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mainPageServlet), "/");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(loginRequestsServlet), "/login");
        context.addServlet(new ServletHolder(goodsRequestsServlet), "/goods/");
        context.addServlet(new ServletHolder(addRequestServlet), "/goods/add");
        context.addServlet(new ServletHolder(updateRequestsServlet), "/goods/update");
        context.addServlet(new ServletHolder(removeRequestsServlet), "/goods/remove");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}