package com.certiorem.microservices.application.controller;

<<<<<<< HEAD
import static org.junit.Assert.assertEquals;

=======
>>>>>>> 468b9b0eb7a9c7b6e547f70bdbde59aca855f69f
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.ModelDataService.Team;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {

	@Autowired
	private CategoryController categoryController;
	@Autowired
	private TeamController teamController;
	@Autowired
	private DriverController driverController;
	
	private Category categorySaved;
	private Team teamSaved;
	private Driver driverSaved;
	
	@Before
	public void beforeTest() throws URISyntaxException {
		
		Team team = new Team();
		team.setNombre("Repsol Honda");
		team.setFundado(1994);
		team.setMotorizador("Honda");
		team.setResponsable("Nakamoto");
		team.setVictorias(998);
		
		List<Team> teams = new ArrayList<Team>();
		teams.add(team);
		
		Driver driver = new Driver();
		driver.setNombre("Marc Marquez");
		driver.setEdad(25);
		driver.setPole(223);
		driver.setVictorias(789);
		driver.setEquipos(teams);
		
		List<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver);
		
		Category category = new Category();
		category.setNombre("TestCategory");
		category.setTipo("Moto");
		category.setFundado(1001);
		category.setEquipos(teams);
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		
		teamSaved = teamController.create(team);
		driverSaved = driverController.create(driver);
		categorySaved = categoryController.create(category);

	}
	
	@After
	public void afterTest() {
		categoryController.delete(categorySaved.getId());
		teamController.delete(teamSaved.getId());
		driverController.delete(driverSaved.getId());
	}
	
	@Test
	@Transactional
	public void testShowCategory() throws URISyntaxException {
		Category category = categoryController.read(categorySaved.getId());
		Category category2 = categoryController.update(category);
		List<Category> categories = categoryController.readAll();
		
		assertEquals(category, categorySaved);
		assertEquals(category, category2);
		
		Driver driver = driverController.read(driverSaved.getId());
		Driver driver2 = driverController.update(driverSaved);
		List<Driver> drivers = driverController.readAll();
		
		Team team = teamController.read(teamSaved.getId());
		Team team2 = teamController.update(teamSaved);
		List<Team> teams = teamController.readAll();

	}
=======
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

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.application.services.CategoryService;
import com.certiorem.microservices.constants.CategoryConstrants;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoryController.class, secure = false)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;

	@Spy
	private List<Category> categories;

	private Category category;

	@Before
	public void beforeTest() throws URISyntaxException {
		category = new Category();
		category.setId(2);
		category.setNombre("MotoGP");
		category.setFundado(1998);
		category.setTipo("Moto");
		category.setEquipos(null);
	}

	@Test
	public void testReadCategory() throws Exception {
		Mockito.when(categoryService.read(Mockito.anyInt())).thenReturn(category);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/read?id=2")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":2,\"nombre\":\"MotoGP\",\"fundado\":1998,\"tipo\":\"Moto\",\"equipos\":null}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testReadCategories() throws Exception {
		categories = new ArrayList<>();
		String expected = "[{\"id\":2,\"nombre\":\"MotoGP\",\"fundado\":1998,\"tipo\":\"Moto\",\"equipos\":null}]";
		categories.add(category);
		Mockito.when(categoryService.readAll()).thenReturn(categories);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(CategoryConstrants.CATEGORY_READ_ALL)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(expected);
		System.out.println(result.getResponse().getContentAsString());
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	public void testCreateCategory() throws Exception {
		String expected = "{\"id\":2,\"nombre\":\"MotoGP\",\"fundado\":1998,\"tipo\":\"Moto\",\"equipos\":null}";
		Mockito.when(categoryService.create(Mockito.any(Category.class))).thenReturn(category);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post(CategoryConstrants.CATEGORY_CREATE).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category)))
				.andExpect(MockMvcResultMatchers.content().string(expected));
	}

	@Test
	public void testDeleteCategory() throws Exception {
		Mockito.doNothing().when(categoryService).delete(Mockito.anyInt());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/category/delete?id=2")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is(200));
	}

>>>>>>> 468b9b0eb7a9c7b6e547f70bdbde59aca855f69f
}
