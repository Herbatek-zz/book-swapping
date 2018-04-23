package com.piotrek.bookswapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.piotrek.bookswapping.repositories")
@EntityScan(basePackages = "com.piotrek.bookswapping.entities")
public class BookSwappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSwappingApplication.class, args);
	}

}
