package com.certiorem.microservices.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.application.services.DriverService;
import com.certiorem.microservices.constants.DriverConstrants;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class DriverController {

	public static final Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	@Autowired
	private DriverService driverService;
	 
	@PostMapping(path = DriverConstrants.DRIVER_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Driver create(@RequestBody Driver driver) throws URISyntaxException {
		return driverService.create(driver);
	}

	@RequestMapping(DriverConstrants.DRIVER_READ)
	Driver read(@RequestParam Integer id) {
		return driverService.read(id);
	}

	@RequestMapping(DriverConstrants.DRIVER_READ_ALL)
	List<Driver> readAll() {
		return driverService.readAll();
	}

	@PostMapping(path = DriverConstrants.DRIVER_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Driver update(@RequestBody Driver driver) throws URISyntaxException {
		return driverService.update(driver);
	}

	@DeleteMapping(DriverConstrants.DRIVER_DELETE)
	void delete(@RequestParam Integer id) {
		driverService.delete(id);
	}
}