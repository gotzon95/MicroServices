package com.certiorem.microservices.application.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.application.services.CategoryService;
import com.certiorem.microservices.constants.CategoryConstrants;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Override
	public Category create(Category category) throws URISyntaxException {
		System.out.println("XAVI - category: " + category);

		String baseUrl = CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}

	@Override
	public Category read(Integer categoryId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", categoryId);

		ResponseEntity<Category> responseEntity = new RestTemplate()
				.getForEntity(CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_READ
						+ "?id={" + CategoryConstrants.CATEGORY_SEARCH_PARAM + "}", Category.class, uriVariables);

		return responseEntity.getBody();
	}

	@Override
	public List<Category> readAll() {
		ResponseEntity<List<Category>> response = new RestTemplate().exchange(
				CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_READ_ALL,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
				});

		return response.getBody();
	}

	@Override
	public void delete(Integer categoryId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", categoryId);

		new RestTemplate().delete(CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST
				+ CategoryConstrants.CATEGORY_DELETE + "?id={" + CategoryConstrants.CATEGORY_SEARCH_PARAM + "}",
				uriVariables);
	}

	@Override
	public Category update(Category category) throws URISyntaxException {
		String baseUrl = CategoryConstrants.CATEGORY_SERVICE_CONTROLLER_HOST + CategoryConstrants.CATEGORY_UPDATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = new RestTemplate().postForEntity(uri, category, Category.class);

		return result.getBody();
	}

}
