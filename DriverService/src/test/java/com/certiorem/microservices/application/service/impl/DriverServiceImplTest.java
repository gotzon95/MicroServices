package com.certiorem.microservices.application.service.impl;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.application.service.DriverService;
import com.certiorem.microservices.constants.DriverConstrants;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceImplTest {

	@InjectMocks
	private DriverService driverService = new DriverServiceImpl();

	@Mock
	private RestTemplate restTemplate;

	private Driver driver;

	@Before
	public void beforeTest() throws URISyntaxException {
		driver = new Driver();
		driver.setId(1);
		driver.setNombre("Gotzon");
		driver.setEdad(23);
		driver.setVictorias(123);
		driver.setPole(251);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testReadDriver() {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", driver.getId());
		Mockito.when(
				restTemplate.getForEntity(Mockito.any(String.class), Mockito.any(Class.class), Mockito.any(Map.class)))
				.thenReturn(new ResponseEntity<Driver>(driver, HttpStatus.ACCEPTED));
		Driver entityDriver = driverService.readDriver(1);
		assertEquals(entityDriver, driver);
	}

	@Test
	public void testReadDrivers() {
		List<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver);

		Mockito.when(restTemplate.exchange(DriverConstrants.DBB_CONNECTOR_CALL_DRIVER, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Driver>>() {
				})).thenReturn(new ResponseEntity<List<Driver>>(drivers, HttpStatus.ACCEPTED));

		List<Driver> allDrivers = driverService.readDrivers();
		assertEquals(drivers, allDrivers);
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	public void testCreateDriver() throws URISyntaxException {

		Mockito.when(restTemplate.postForEntity(Mockito.any(URI.class), Mockito.any(Object.class),
				Mockito.any(Class.class))).thenReturn(new ResponseEntity<Driver>(driver, HttpStatus.ACCEPTED));
		System.out.println(driver);

		Driver entityDriver = driverService.create(driver);
		System.out.println(entityDriver);
		assertEquals(entityDriver, driver);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDeleteTeam() {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", driver.getId());
		Mockito.doNothing().when(restTemplate).delete(Mockito.any(String.class), Mockito.any(Map.class));
		driverService.delete(1);
	}

}
