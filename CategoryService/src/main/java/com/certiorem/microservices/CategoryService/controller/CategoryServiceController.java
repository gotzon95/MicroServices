package com.certiorem.microservices.CategoryService.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.constants.URLConstants;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class CategoryServiceController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryServiceController.class);
	 
	@RequestMapping(value = "/category/read")
	@ResponseBody
	public Category readCategory(@RequestParam String id) {
		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Category> responseEntity = new RestTemplate().getForEntity(URLConstants.CATEGORY_SERVIVE_GET_CATEGORY,
				Category.class, uriVariables);
		return responseEntity.getBody();
	}
	
	@RequestMapping(value = "/category/readAll")
	@ResponseBody
	public List<Category> readCategories() {
		ResponseEntity<List<Category>> response = new RestTemplate().exchange("http://192.168.1.117:8093/category/readAllCategories",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
				});

		return response.getBody();
	}
	
	@RequestMapping(value = "/category/delete")
	@ResponseBody
	void delete(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		new RestTemplate().delete("http://192.168.1.117:8093/category/deleteItem?id={id}", uriVariables);

	}
	

	
	@PostMapping(path = "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Category create(@RequestBody Category category) throws URISyntaxException {

		System.out.println("XAVI - driver: " + category);
		
		String baseUrl = "http://192.168.1.117:8093/category/createItem";
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}
	
	
}