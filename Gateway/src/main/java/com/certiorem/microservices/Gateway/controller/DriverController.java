package com.certiorem.microservices.Gateway.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.constants.DriverConstrants;

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
	 
	@PostMapping(path = DriverConstrants.DRIVER_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Driver create(@RequestBody Driver driver) throws URISyntaxException {

		System.out.println("XAVI - driver: " + driver);
		
		String baseUrl = DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = new RestTemplate().postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}

	@RequestMapping(DriverConstrants.DRIVER_READ)
	Driver read(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Driver> responseEntity = new RestTemplate().getForEntity(DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_READ + "?id={" + DriverConstrants.DRIVER_SEARCH_PARAM + "}",
				Driver.class, uriVariables);
		

		return responseEntity.getBody();
	}

	@RequestMapping(DriverConstrants.DRIVER_READ_ALL)
	List<Driver> read() {

		ResponseEntity<List<Driver>> response = new RestTemplate().exchange(DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_READ_ALL,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {
				});

		return response.getBody();
	}

	@PostMapping(path = DriverConstrants.DRIVER_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Driver update(@RequestBody Driver driver) throws URISyntaxException {

		System.out.println("XAVI - driver: " + driver);
		
		String baseUrl = DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_UPDATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = new RestTemplate().postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}

	@DeleteMapping(DriverConstrants.DRIVER_DELETE)
	void delete(@RequestParam Integer id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		new RestTemplate().delete(DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_DELETE + "?id={" + DriverConstrants.DRIVER_SEARCH_PARAM + "}", uriVariables);
		
	}
}