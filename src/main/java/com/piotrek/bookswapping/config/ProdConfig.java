package com.piotrek.bookswapping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("classpath:profiles/application-prod.properties")
public class ProdConfig {
}
