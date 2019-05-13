package com.certiorem.microservices.CategoryService.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.constants.CategoryConstrants;
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
	 
	@RequestMapping(value = CategoryConstrants.CATEGORY_READ)
	@ResponseBody
	public Category readCategory(@RequestParam Integer id) {
		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		ResponseEntity<Category> responseEntity = new RestTemplate().getForEntity(CategoryConstrants.CATEGORY_SERVICE_GET_CATEGORY,
				Category.class, uriVariables);
		return responseEntity.getBody();
	}
	
	@RequestMapping(value = CategoryConstrants.CATEGORY_READ_ALL)
	@ResponseBody
	public List<Category> readCategories() {
		ResponseEntity<List<Category>> response = new RestTemplate().exchange(CategoryConstrants.DBB_CONNECTOR_CALL_CATEGORY,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
				});

		return response.getBody();
	}
	
	@DeleteMapping(CategoryConstrants.CATEGORY_DELETE)
	@ResponseBody
	void delete(@RequestParam Integer id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		new RestTemplate().delete(CategoryConstrants.DBB_CONNECTOR_DELETE_CATEGORY, uriVariables);

	}
	

	
	@PostMapping(path = CategoryConstrants.CATEGORY_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Category create(@RequestBody Category category) throws URISyntaxException {

		System.out.println("XAVI - driver: " + category);
		
		String baseUrl = CategoryConstrants.DBB_CONNECTOR_CREATE_CATEGORY;
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}
	
	
}