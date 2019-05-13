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
import com.certiorem.microservices.constants.CategoryConstrants;

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
	 
	@PostMapping(path = CategoryConstrants.CATEGORY_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Category create(@RequestBody Category category) throws URISyntaxException {

		System.out.println("XAVI - category: " + category);
		
		String baseUrl = CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}

	@RequestMapping(CategoryConstrants.CATEGORY_READ)
	Category read(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Category> responseEntity = new RestTemplate().getForEntity(CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_READ + "?id={" + CategoryConstrants.CATEGORY_SEARCH_PARAM + "}", 
				Category.class, uriVariables);

		return responseEntity.getBody();
	}

	@RequestMapping(CategoryConstrants.CATEGORY_READ_ALL)
	List<Category> read() {

		ResponseEntity<List<Category>> response = new RestTemplate().exchange(CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_READ_ALL,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
				});

		return response.getBody();
	}

	@PostMapping(path = CategoryConstrants.CATEGORY_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Category update(@RequestBody Category category) throws URISyntaxException {

		System.out.println("XAVI - category: " + category);
		
		String baseUrl = CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_UPDATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}

	@RequestMapping(CategoryConstrants.CATEGORY_DELETE)
	void delete(@RequestParam Integer id) {

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		new RestTemplate().delete(CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_DELETE + "?id={" + CategoryConstrants.CATEGORY_SEARCH_PARAM + "}",
				uriVariables);
	}
}