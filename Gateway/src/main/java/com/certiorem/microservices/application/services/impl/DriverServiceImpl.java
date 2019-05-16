package com.certiorem.microservices.application.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.application.services.DriverService;
import com.certiorem.microservices.constants.DriverConstrants;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private RestTemplate restTemplate;
    
	@Override
	public Driver create(Driver driver) throws URISyntaxException {
		String baseUrl = DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = restTemplate.postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}

	@Override
	public Driver read(Integer driverId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", driverId);

		ResponseEntity<Driver> responseEntity = restTemplate.getForEntity(DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_READ + "?id={" + DriverConstrants.DRIVER_SEARCH_PARAM + "}",
				Driver.class, uriVariables);
		

		return responseEntity.getBody();
	}

	@Override
	public List<Driver> readAll() {
		ResponseEntity<List<Driver>> response = restTemplate.exchange(DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_READ_ALL,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {
				});

		return response.getBody();
	}

	@Override
	public void delete(Integer driverId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", driverId);

		restTemplate.delete(DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_DELETE + "?id={" + DriverConstrants.DRIVER_SEARCH_PARAM + "}", uriVariables);
	}

	@Override
	public Driver update(Driver driver) throws URISyntaxException {
		String baseUrl = DriverConstrants.DRIVER_SERVICE_CONTROLLER_HOST + DriverConstrants.DRIVER_UPDATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Driver> result = restTemplate.postForEntity(uri, driver, Driver.class);

		return result.getBody();
	}

}
