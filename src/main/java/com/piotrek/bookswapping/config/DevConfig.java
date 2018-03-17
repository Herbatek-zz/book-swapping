package com.piotrek.bookswapping.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.h2.server.web.WebServlet;

@Configuration
@Profile("dev")
@PropertySource("classpath:profiles/application-dev.properties")
public class DevConfig {

    @Bean
    public ServletRegistrationBean<WebServlet> h2ConsoleServletRegistration() {
        ServletRegistrationBean<WebServlet> bean = new ServletRegistrationBean<>(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }
}
