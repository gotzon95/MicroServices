package com.certiorem.microservices.DatabaseService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.CategoryService;
import com.certiorem.microservices.ModelDataService.Category;

@RestController
class CategoryApiController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryApiController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/category")
	Category showCategory(@RequestParam String id) {

		return getCategoryInfo(id);
	}
	
	@RequestMapping("/category/readAllCategories")
	List<Category> showAllCategories() {
		List<Category> categories = categoryService.findAllCategories();
		return categories;
	}

	private Category getCategoryInfo(String id) {
		System.out.println(id);
		Category category = categoryService.findById(Integer.parseInt(id));
		System.out.println(category);
		
		return category;
	}
	
	@RequestMapping("/category/deleteItem")
	void deleteCat(@PathVariable Integer id) {
		deleteCategory(id);
	}
	
	@PostMapping("/category/createItem")
	Category createCat(@RequestBody Category category) {
		return createCategory(category);
	}
	
	private Category createCategory(Category category) {
		System.out.println("JON - createCategory, category: " + category);
		return categoryService.save(category);
	}
	
	private void deleteCategory(Integer id) {
		categoryService.delete(id);
		
		System.out.println("Deleted");
	}
	
	@PostMapping("/category/updateItem")
	Category updateCategory(@RequestBody Category category) {
		return createCategory(category);
	}
	
}