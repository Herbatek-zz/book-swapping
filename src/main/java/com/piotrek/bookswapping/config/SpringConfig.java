package com.piotrek.bookswapping.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // http -> https
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(redirectConnector());
//        return tomcat;
//    }
//
//    private Connector redirectConnector() {
//        Connector connector = new Connector(
//                TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//        return connector;
//    }

}
