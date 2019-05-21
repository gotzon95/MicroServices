package com.certiorem.microservices.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CompetitionServiceApp extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(CompetitionServiceApp.class, args);
	}
}
