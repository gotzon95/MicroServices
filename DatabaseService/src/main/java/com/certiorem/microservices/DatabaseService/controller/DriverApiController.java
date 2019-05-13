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
import com.certiorem.microservices.constants.DriverConstrants;

@RestController
class DriverApiController {

	public static final Logger logger = LoggerFactory.getLogger(DriverApiController.class);

	@Autowired
	private DriverService driverService;
	
	@GetMapping(DriverConstrants.DRIVER_CLASS_PARAM)
	Driver showDriver(@RequestParam Integer id) {

		return getDriverInfo(id);
	}
	
	@RequestMapping(DriverConstrants.DRIVER_CLASS_PARAM + DriverConstrants.DRIVER_MICROSERVICE_READ)
	List<Driver> showAllDrivers() {
		List<Driver> drivers = driverService.findAllDrivers();
		return drivers;
	}
	
	@DeleteMapping(DriverConstrants.DRIVER_CLASS_PARAM + DriverConstrants.DRIVER_MICROSERVICE_DELETE)
	void deletedriv(@PathVariable Integer id) {
		deleteDriver(id);
	}
	
	@PostMapping(DriverConstrants.DRIVER_CLASS_PARAM + DriverConstrants.DRIVER_MICROSERVICE_CREATE)
	Driver createDrvr(@RequestBody Driver driver) {
		return createDriver(driver);
	}

	@PostMapping(DriverConstrants.DRIVER_CLASS_PARAM + DriverConstrants.DRIVER_MICROSERVICE_UPDATE)
	Driver updateDriver(@RequestBody Driver driver) {
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
	
	private Driver getDriverInfo(Integer id) {
		System.out.println(id);
		Driver driver = driverService.findById(id);
		System.out.println(driver);

		return driver;
	}
	
}