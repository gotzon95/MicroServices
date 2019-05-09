package com.certiorem.microservices.Gateway.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Category;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class CategoryController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	 
	@PostMapping(path = "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Category create(@RequestBody Category category) throws URISyntaxException {

		System.out.println("XAVI - category: " + category);
		
		String baseUrl = "http://localhost:8092/category/create";
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}

	@RequestMapping("category/read")
	Category read(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Category> responseEntity = new RestTemplate().getForEntity("http://localhost:8092/category/read?id={id}",
				Category.class, uriVariables);

		return responseEntity.getBody();
	}

	@RequestMapping("category/readAll")
	List<Category> read() {

		ResponseEntity<List<Category>> response = new RestTemplate().exchange("http://localhost:8092/category/readAll",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
				});

		return response.getBody();
	}

	@PostMapping(path = "/category/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Category update(@RequestBody Category category) throws URISyntaxException {

		System.out.println("XAVI - category: " + category);
		
		String baseUrl = "http://localhost:8092/category/update";
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}

	@RequestMapping("category/delete")
	Category delete(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Category> responseEntity = new RestTemplate().getForEntity("http://localhost:8092/category/delete?id={id}",
				Category.class, uriVariables);

		return responseEntity.getBody();
	}
}