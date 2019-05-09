package com.certiorem.microservices.DriverService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Hello world!
 *
 */
@SpringBootApplication
public class DriverServiceApp extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(DriverServiceApp.class, args);
	}
}
