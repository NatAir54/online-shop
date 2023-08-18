package com.studying.onlineshop;

import com.studying.ioc.context.ApplicationContext;
import com.studying.ioc.context.ClassPathXmlApplicationContext;
import com.studying.onlineshop.service.ClientService;
import com.studying.onlineshop.service.SecurityService;
import com.studying.onlineshop.web.filter.SecurityFilter;
import com.studying.onlineshop.web.servlet.*;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import java.util.EnumSet;


public class Starter {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("src/main/resources/os-context-1.xml", "src/main/resources/os-context-2.xml");

        ClientService clientService = applicationContext.getBean(ClientService.class);
        SecurityService securityService =  applicationContext.getBean(SecurityService.class);

        MainPageServlet mainPageServlet = new MainPageServlet();
        GoodsListServlet goodsRequestsServlet = applicationContext.getBean(GoodsListServlet.class);
        AddGoodsServlet addRequestServlet = applicationContext.getBean(AddGoodsServlet.class);
        UpdateGoodsServlet updateRequestsServlet = applicationContext.getBean(UpdateGoodsServlet.class);
        RemoveGoodsServlet removeRequestsServlet = applicationContext.getBean(RemoveGoodsServlet.class);
        SignUpServlet signUpServlet = new SignUpServlet(securityService, clientService);
        LoginServlet loginRequestsServlet = new LoginServlet(clientService, securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addFilter(new FilterHolder(new SecurityFilter(securityService)), "/*", EnumSet.of(DispatcherType.REQUEST));

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