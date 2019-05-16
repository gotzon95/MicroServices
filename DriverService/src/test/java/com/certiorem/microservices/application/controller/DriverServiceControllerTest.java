package com.certiorem.microservices.application.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.application.service.DriverService;
import com.certiorem.microservices.constants.DriverConstrants;
import com.certiorem.microservices.constants.TeamConstrants;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DriverServiceController.class, secure = false)
public class DriverServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DriverService driverService;

	@Spy
	private List<Driver> drivers;

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

	@Test
	public void testReadDriver() throws Exception {
		Mockito.when(driverService.readDriver(Mockito.anyInt())).thenReturn(driver);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/driver/read?id=1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":1,\"nombre\":\"Gotzon\",\"edad\":23,\"victorias\":123,\"pole\":251,\"equipos\":null}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testReadDrivers() throws Exception {
		drivers = new ArrayList<>();
		drivers.add(driver);
		Mockito.when(driverService.readDrivers()).thenReturn(drivers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(DriverConstrants.DRIVER_READ_ALL)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected ="[{\"id\":1,\"nombre\":\"Gotzon\",\"edad\":23,\"victorias\":123,\"pole\":251,\"equipos\":null}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testCreateDriver() throws Exception {
		String expected = "{\"id\":1,\"nombre\":\"Gotzon\",\"edad\":23,\"victorias\":123,\"pole\":251,\"equipos\":null}";
		Mockito.when(driverService.create(Mockito.any(Driver.class))).thenReturn(driver);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post(DriverConstrants.DRIVER_CREATE).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driver)))
				.andExpect(MockMvcResultMatchers.content().string(expected));
	}

	@Test
	public void testDeleteDriver() throws Exception {
		Mockito.doNothing().when(driverService).delete(Mockito.anyInt());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/driver/delete?id=1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
