package com.certiorem.microservices.DatabaseService.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/category/delete")
	void deleteCat(@PathVariable Long id) {
		deleteCategory(id);
	}
	
	@RequestMapping("/category/create")
	private Category createCategory(@RequestBody Map<String, String> body) {
		String name = body.get("name");
		
		return categoryService.save(new Category());
	}
	
	@RequestMapping("/driver/update/{id}")
	public Category update(@PathVariable String id, @RequestBody Map<String, String> body) {
		long categoryId = Long.parseLong(id);
		
		Category category = categoryService.findById(categoryId);
		category.setNombre(body.get("name"));
		//driver.setVictorias(body.get("victory"));
		return categoryService.save(category);
	}
	
	private void deleteCategory(Long id) {
		categoryService.delete(id);
		
		System.out.println("Deleted");
	}
}