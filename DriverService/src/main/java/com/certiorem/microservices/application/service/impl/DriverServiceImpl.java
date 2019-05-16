package com.certiorem.microservices.application.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.application.service.DriverService;
import com.certiorem.microservices.constants.DriverConstrants;

@Service
public class DriverServiceImpl implements DriverService{

	@Override
	public Driver readDriver(Integer driverId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", driverId);

		ResponseEntity<Driver> responseEntity = new RestTemplate().getForEntity(DriverConstrants.DRIVER_SERVICE_GET_DRIVER,
				Driver.class, uriVariables);
		return responseEntity.getBody();
	}

	@Override
	public List<Driver> readDrivers() {
		ResponseEntity<List<Driver>> response = new RestTemplate().exchange(DriverConstrants.DBB_CONNECTOR_CALL_DRIVER,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {
				});

		return response.getBody();
	}

	@Override
	public Driver create(Driver driver) throws URISyntaxException {
		String baseUrl = DriverConstrants.DBB_CONNECTOR_CREATE_DRIVER;
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = new RestTemplate().postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}

	@Override
	public void delete(Integer driverId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", driverId);

		new RestTemplate().delete(DriverConstrants.DBB_CONNECTOR_DELETE_DRIVER, uriVariables);
	}


	
	
}
