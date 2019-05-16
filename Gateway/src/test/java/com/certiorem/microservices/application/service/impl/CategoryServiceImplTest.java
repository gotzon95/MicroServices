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

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.application.services.CategoryService;
import com.certiorem.microservices.application.services.impl.CategoryServiceImpl;
import com.certiorem.microservices.constants.CategoryConstrants;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

	@InjectMocks
	private CategoryService categoryService = new CategoryServiceImpl();

	@Mock
	private RestTemplate restTemplate;

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

	@SuppressWarnings("unchecked")
	@Test
	public void testReadTeam() {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", category.getId());
		Mockito.when(
				restTemplate.getForEntity(Mockito.any(String.class), Mockito.any(Class.class), Mockito.any(Map.class)))
				.thenReturn(new ResponseEntity<Category>(category, HttpStatus.ACCEPTED));
		Category entityTeam = categoryService.read(1);
		assertEquals(entityTeam, category);
		System.out.println(entityTeam);
		System.out.println(category);
	}

	@Test
	public void testReadTeams() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);

		Mockito.when(restTemplate.exchange(CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_READ_ALL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Category>>() {
				})).thenReturn(new ResponseEntity<List<Category>>(categories, HttpStatus.ACCEPTED));

		List<Category> allCategories = categoryService.readAll();
		assertEquals(categories, allCategories);
		System.out.println(categories);
		System.out.println(allCategories);
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	public void testCreateTeam() throws URISyntaxException {

		Mockito.when(restTemplate.postForEntity(Mockito.any(URI.class), Mockito.any(Object.class),
				Mockito.any(Class.class))).thenReturn(new ResponseEntity<Category>(category, HttpStatus.ACCEPTED));
		System.out.println(category);

		Category entityTeam = categoryService.create(category);
		System.out.println(entityTeam);
		System.out.println(category);
		assertEquals(entityTeam, category);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDeleteTeam() {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", category.getId());
		Mockito.doNothing().when(restTemplate).delete(Mockito.any(String.class), Mockito.any(Map.class));
		categoryService.delete(2);
	}

}
