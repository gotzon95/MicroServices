package com.certiorem.microservices.application.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.application.service.CategoryService;
import com.certiorem.microservices.constants.CategoryConstrants;

@Service
public class CategoryServiceImpl implements CategoryService {
	
    @Autowired
    private RestTemplate restTemplate;
    
	@Override
	public Category readCategory(Integer categoryId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", categoryId);

		ResponseEntity<Category> responseEntity = restTemplate.getForEntity(CategoryConstrants.CATEGORY_SERVICE_GET_CATEGORY, Category.class, uriVariables);
		return responseEntity.getBody();
	}

	@Override
	public List<Category> readCategories() {
		ResponseEntity<List<Category>> response = restTemplate.exchange(
				CategoryConstrants.DBB_CONNECTOR_CALL_CATEGORY, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Category>>() {
				});

		return response.getBody();
	}

	@Override
	public void delete(Integer categoryId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", categoryId);

		restTemplate.delete(CategoryConstrants.DBB_CONNECTOR_DELETE_CATEGORY, uriVariables);
	}

	@Override
	public Category create(Category category) throws URISyntaxException {
		String baseUrl = CategoryConstrants.DBB_CONNECTOR_CREATE_CATEGORY;
		URI uri = new URI(baseUrl);

		ResponseEntity<Category> result = restTemplate.postForEntity(uri, category, Category.class);

		return result.getBody();
	}

	
}
