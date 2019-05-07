package com.certiorem.microservices.DatabaseService.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.DriverService;
import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.ModelDataService.Team;

@RestController
class DriverApiController {

	public static final Logger logger = LoggerFactory.getLogger(DriverApiController.class);

	
	@Autowired
	private DriverService driverService;

	
	@RequestMapping("/driver/{name}")
	Driver showDriver(@PathVariable String name) {

		return getDriverInfo(name);
	}
	
	@RequestMapping("/driver/delete")
	void deletedriv(@PathVariable Long id) {
		deleteDriver(id);
	}

	private Driver getDriverInfo(String name) {
		System.out.println(name);
		Driver driver = driverService.findByName(name);
		System.out.println(driver);

		return driver;
	}
	
	@RequestMapping("/driver/create")
	private Driver createDriver(@RequestBody Map<String, String> body) {
		String name = body.get("name");
		
		return driverService.save(new Driver());
	}
	
	@RequestMapping("/driver/update/{id}")
	public Driver update(@PathVariable String id, @RequestBody Map<String, String> body) {
		long driverId = Long.parseLong(id);
		
		Driver driver = driverService.findById(driverId);
		driver.setNombre(body.get("name"));
		//driver.setVictorias(body.get("victory"));
		return driverService.save(driver);
	}
	
	private void deleteDriver(@PathVariable Long id) {
		driverService.delete(id);
		
		System.out.println("Deleted");
	}
	
}