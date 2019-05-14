package com.certiorem.microservices.DatabaseService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.CategoryService;
import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.constants.CategoryConstrants;

@RestController
class CategoryApiController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryApiController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(CategoryConstrants.CATEGORY_CLASS_PARAM)
	Category showCategory(@RequestParam Integer id) {
		return categoryService.findById(id);
	}
	
	@RequestMapping(CategoryConstrants.CATEGORY_CLASS_PARAM + CategoryConstrants.CATEGORY_MICROSERVICE_READ)
	List<Category> showAllCategories() {
		List<Category> categories = categoryService.findAllCategories();
		return categories;
	}

	@DeleteMapping(CategoryConstrants.CATEGORY_CLASS_PARAM + CategoryConstrants.CATEGORY_MICROSERVICE_DELETE)
	void deleteCat(@RequestParam Integer id) {
		categoryService.delete(id);
	}
	
	@PostMapping(CategoryConstrants.CATEGORY_CLASS_PARAM + CategoryConstrants.CATEGORY_MICROSERVICE_CREATE)
	Category createCat(@RequestBody Category category) {
		return categoryService.save(category);
	}
	
	@PostMapping(CategoryConstrants.CATEGORY_CLASS_PARAM + CategoryConstrants.CATEGORY_MICROSERVICE_UPDATE)
	Category updateCategory(@RequestBody Category category) {
		return categoryService.save(category);
	}

}