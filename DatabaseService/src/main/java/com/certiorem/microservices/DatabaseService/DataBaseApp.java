package com.certiorem.microservices.DatabaseService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.certiorem.microservices.DatabaseService.repositories")
@EntityScan(basePackages = { "com.certiorem.microservices.ModelDataService" })
public class DataBaseApp extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(DataBaseApp.class, args);
	}
}