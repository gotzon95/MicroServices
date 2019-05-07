package com.certiorem.microservices.DatabaseService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.CategoryService;
import com.certiorem.microservices.ModelDataService.Category;

@RestController
class CategoryApiController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryApiController.class);

	
	@Autowired
	private CategoryService categoryService;

	
	@RequestMapping("/category/{name}")
	Category showCategory(@PathVariable String name) {

		return getCategoryInfo(name);
	}

	private Category getCategoryInfo(String name) {
		System.out.println(name);
		Category category = categoryService.findByName(name);
		System.out.println(category);
		
		return category;
	}
}