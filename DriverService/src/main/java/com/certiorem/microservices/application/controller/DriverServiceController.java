package com.certiorem.microservices.DriverService.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.constants.DriverConstrants;
import com.certiorem.microservices.constants.URLConstants;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class DriverServiceController {

	public static final Logger logger = LoggerFactory.getLogger(DriverServiceController.class);
	 
	@RequestMapping(value = DriverConstrants.DRIVER_READ)
	@ResponseBody
	public Driver readDriver(@RequestParam Integer id) {
		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		ResponseEntity<Driver> responseEntity = new RestTemplate().getForEntity(DriverConstrants.DRIVER_SERVICE_GET_DRIVER,
				Driver.class, uriVariables);
		return responseEntity.getBody();
	}
	
	@RequestMapping(value = DriverConstrants.DRIVER_READ_ALL)
	@ResponseBody
	public List<Driver> readDrivers() {
		ResponseEntity<List<Driver>> response = new RestTemplate().exchange(DriverConstrants.DBB_CONNECTOR_CALL_DRIVER,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {
				});

		return response.getBody();
	}
	
	@RequestMapping(value = DriverConstrants.DRIVER_DELETE)
	@ResponseBody
	void delete(@RequestParam Integer id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		new RestTemplate().delete(DriverConstrants.DBB_CONNECTOR_DELETE_DRIVER, uriVariables);

	}
	

	
	@PostMapping(path = DriverConstrants.DRIVER_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Driver create(@RequestBody Driver driver) throws URISyntaxException {

		System.out.println("XAVI - driver: " + driver);
		
		String baseUrl = DriverConstrants.DBB_CONNECTOR_CREATE_DRIVER;
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = new RestTemplate().postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}
	
	
}