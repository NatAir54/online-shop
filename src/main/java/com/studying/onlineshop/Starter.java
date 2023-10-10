package com.studying.onlineshop;

import com.studying.onlineshop.service.SecurityService;
import com.studying.onlineshop.web.filter.SecurityFilter;
import com.studying.onlineshop.web.servlet.*;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.EnumSet;


public class Starter {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

        SecurityService securityService =  applicationContext.getBean(SecurityService.class);
        MainPageServlet mainPageServlet = applicationContext.getBean(MainPageServlet.class);
        GoodsListServlet goodsRequestsServlet = applicationContext.getBean(GoodsListServlet.class);
        AddGoodsServlet addRequestServlet = applicationContext.getBean(AddGoodsServlet.class);
        UpdateGoodsServlet updateRequestsServlet = applicationContext.getBean(UpdateGoodsServlet.class);
        RemoveGoodsServlet removeRequestsServlet = applicationContext.getBean(RemoveGoodsServlet.class);
        SignUpServlet signUpServlet = applicationContext.getBean(SignUpServlet.class);
        LoginServlet loginRequestsServlet = applicationContext.getBean(LoginServlet.class);

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