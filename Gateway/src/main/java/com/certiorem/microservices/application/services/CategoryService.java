package com.certiorem.microservices.application.services;

import java.net.URISyntaxException;
import java.util.List;


import com.certiorem.microservices.ModelDataService.Category;


public interface CategoryService {

	public Category create(Category category) throws URISyntaxException;
	
	public Category read(Integer categoryId);
	
	public List<Category> readAll();
	
	public void delete(Integer categoryId);
	
	public Category update(Category category) throws URISyntaxException;
}
