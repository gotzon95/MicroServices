package com.certiorem.microservices.DatabaseService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.DriverService;
import com.certiorem.microservices.ModelDataService.Driver;

@RestController
class DriverApiController {

	public static final Logger logger = LoggerFactory.getLogger(DriverApiController.class);

	@Autowired
	private DriverService driverService;
	
	@GetMapping("/driver")
	Driver showDriver(@RequestParam String name) {

		return getDriverInfo(name);
	}
	
	@RequestMapping("/driver/readAllDrivers")
	List<Driver> showAllDrivers() {
		List<Driver> drivers = driverService.findAllDrivers();
		return drivers;
	}
	
	@DeleteMapping("/driver/deleteItem")
	void deletedriv(@PathVariable Integer id) {
		deleteDriver(id);
	}

	private Driver getDriverInfo(String name) {
		System.out.println(name);
		Driver driver = driverService.findByName(name);
		System.out.println(driver);

		return driver;
	}
	
	@PostMapping("/driver/createItem")
	Driver createDrvr(@RequestBody Driver driver) {
		return createDriver(driver);
	}
	
	private Driver createDriver(Driver driver) {
		System.out.println("JON - createDriver, driver: " + driver);
		return driverService.save(driver);
	}
	
	private void deleteDriver(@PathVariable Integer id) {
		driverService.delete(id);
		
		System.out.println("Deleted");
	}
	
	@PostMapping("/driver/updateItem")
	Driver updateDriver(@RequestBody Driver driver) {
		return createDriver(driver);
	}
	
}