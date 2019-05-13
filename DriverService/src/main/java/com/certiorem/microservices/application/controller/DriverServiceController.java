package com.certiorem.microservices.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.application.service.DriverService;
import com.certiorem.microservices.constants.DriverConstrants;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class DriverServiceController {

	public static final Logger logger = LoggerFactory.getLogger(DriverServiceController.class);
	 
	@Autowired
	private DriverService driverService;
	
	@RequestMapping(value = DriverConstrants.DRIVER_READ)
	@ResponseBody
	public Driver readDriver(@RequestParam Integer id) {
		return driverService.readDriver(id);
	}
	
	@RequestMapping(value = DriverConstrants.DRIVER_READ_ALL)
	@ResponseBody
	public List<Driver> readDrivers() {
		return driverService.readDrivers();
	}
	
	@RequestMapping(value = DriverConstrants.DRIVER_DELETE)
	@ResponseBody
	void delete(@RequestParam Integer id) {
		driverService.delete(id);
	}
	
	@PostMapping(path = DriverConstrants.DRIVER_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Driver create(@RequestBody Driver driver) throws URISyntaxException {
		return driverService.create(driver);
	}
	
	
}