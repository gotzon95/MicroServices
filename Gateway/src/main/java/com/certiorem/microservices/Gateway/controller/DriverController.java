package com.certiorem.microservices.Gateway.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Driver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class DriverController {

	public static final Logger logger = LoggerFactory.getLogger(DriverController.class);
	 
	@PostMapping(path = "/driver/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Driver create(@RequestBody Driver driver) throws URISyntaxException {

		System.out.println("XAVI - driver: " + driver);
		
		String baseUrl = "http://localhost:8091/driver/create";
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = new RestTemplate().postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}

	@RequestMapping("driver/read")
	Driver read(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Driver> responseEntity = new RestTemplate().getForEntity("http://localhost:8091/driver/read?id={id}",
				Driver.class, uriVariables);

		return responseEntity.getBody();
	}

	@RequestMapping("driver/readAll")
	List<Driver> read() {

		ResponseEntity<List<Driver>> response = new RestTemplate().exchange("http://localhost:8091/driver/readAll",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {
				});

		return response.getBody();
	}

	@PostMapping(path = "/driver/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Driver update(@RequestBody Driver driver) throws URISyntaxException {

		System.out.println("XAVI - driver: " + driver);
		
		String baseUrl = "http://localhost:8091/driver/update";
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = new RestTemplate().postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}

	@RequestMapping("driver/delete")
	Driver delete(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Driver> responseEntity = new RestTemplate().getForEntity("http://localhost:8091/driver/delete?id={id}",
				Driver.class, uriVariables);

		return responseEntity.getBody();
	}
}