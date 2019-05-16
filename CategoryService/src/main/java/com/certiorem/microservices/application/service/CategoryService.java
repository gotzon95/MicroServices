package com.certiorem.microservices.application.service;

import java.net.URISyntaxException;
import java.util.List;

import com.certiorem.microservices.ModelDataService.Category;

public interface CategoryService {
	
	public Category readCategory(Integer categoryId);
	
	public List<Category> readCategories();
	
	public void delete(Integer categoryId);
	
	public Category create(Category category) throws URISyntaxException;
}
