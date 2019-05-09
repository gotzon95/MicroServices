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
	 
	@RequestMapping(value = "/driver/read")
	@ResponseBody
	public Driver readDriver(@RequestParam String name) {
		System.out.println("Gotzon - Gateway id: " + name);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("name", name);

		ResponseEntity<Driver> responseEntity = new RestTemplate().getForEntity(URLConstants.DRIVER_SERVIVE_GET_DRIVER,
				Driver.class, uriVariables);
		return responseEntity.getBody();
	}
	
	@RequestMapping(value = "/driver/readAll")
	@ResponseBody
	public List<Driver> readDrivers() {
		ResponseEntity<List<Driver>> response = new RestTemplate().exchange("http://192.168.1.117:8093/driver/readAllDrivers",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {
				});

		return response.getBody();
	}
	
	@RequestMapping(value = "/driver/deleteDriver")
	@ResponseBody
	void delete(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		new RestTemplate().delete("http://192.168.1.117:8093/driver/deleteItem?id={id}", uriVariables);

	}
	

	
	@PostMapping(path = "/driver/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Driver create(@RequestBody Driver driver) throws URISyntaxException {

		System.out.println("XAVI - driver: " + driver);
		
		String baseUrl = "http://192.168.1.117:8093/driver/createItem";
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = new RestTemplate().postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}
	
	
}