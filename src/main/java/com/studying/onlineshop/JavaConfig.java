package com.studying.onlineshop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.studying.onlineshop.service, com.studying.onlineshop.web.servlet, com.studying.onlineshop.dao")
public class JavaConfig {
}
