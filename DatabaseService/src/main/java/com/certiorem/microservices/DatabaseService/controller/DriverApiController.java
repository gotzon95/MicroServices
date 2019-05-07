package com.certiorem.microservices.DatabaseService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.DriverService;
import com.certiorem.microservices.ModelDataService.Driver;

@RestController
class DriverApiController {

	public static final Logger logger = LoggerFactory.getLogger(DriverApiController.class);

	
	@Autowired
	private DriverService driverService;

	
	@RequestMapping("/driver/{name}")
	Driver showDriver(@PathVariable String name) {

		return getDriverInfo(name);
	}

	private Driver getDriverInfo(String name) {
		System.out.println(name);
		Driver driver = driverService.findByName(name);
		System.out.println(driver);

		return driver;
	}
}