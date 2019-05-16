package com.certiorem.microservices.application.controller;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
}
