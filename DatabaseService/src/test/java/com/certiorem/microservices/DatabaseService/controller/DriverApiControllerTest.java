package com.certiorem.microservices.DatabaseService.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.ModelDataService.Driver;
import com.certiorem.microservices.ModelDataService.Team;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DriverApiControllerTest {

	@Autowired
	private CategoryApiController categoryController;
	@Autowired
	private TeamApiController teamController;
	@Autowired
	private DriverApiController driverController;
	
	private Category categorySaved;
	private Team teamSaved;
	private Driver driverSaved;
	
	@Before
	public void beforeTest() {
		
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
		
		teamSaved = teamController.createTeam(team);
		driverSaved = driverController.createDrvr(driver);
		categorySaved = categoryController.createCat(category);

	}
	
	@After
	public void afterTest() {
		categoryController.deleteCat(categorySaved.getId());
		teamController.deleteTm(teamSaved.getId());
		driverController.deletedriv(driverSaved.getId());
	}
	
	@Test
	@Transactional
	public void testShowDriver() {
		Driver driver = driverController.showDriver(driverSaved.getId());
		assertEquals(driver, driverSaved);
	}
	
	@Test
	@Transactional
	public void showAllDrivers() {
		List<Driver> drivers = driverController.showAllDrivers();
		boolean result = drivers.contains(driverSaved);
		assertTrue(result);
	}
	
	@Test
	@Transactional
	public void updateDriver() {
		String changedName = "Updated";
		Driver driverReaded = driverController.showDriver(driverSaved.getId());
		driverReaded.setNombre(changedName);
		
		categorySaved.setNombre(changedName);
		
		Driver driver = driverController.updateDriver(driverReaded);
		assertEquals(driver, driverSaved);
	}
}
