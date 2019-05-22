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

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.ModelDataService.CategoryType;
import com.certiorem.microservices.application.service.CategoryService;
import com.certiorem.microservices.constants.CategoryConstrants;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoryServiceController.class, secure = false)
public class CategoryServiceControllerTest {

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
		category.setTipo(CategoryType.MOTOGP);
		category.setEquipos(null);
	}

	@Test
	public void testReadCategory() throws Exception {
		Mockito.when(categoryService.readCategory(Mockito.anyInt())).thenReturn(category);
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
		Mockito.when(categoryService.readCategories()).thenReturn(categories);
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

}
