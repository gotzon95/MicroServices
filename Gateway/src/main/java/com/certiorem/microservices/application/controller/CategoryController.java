package com.certiorem.microservices.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.application.services.CategoryService;
import com.certiorem.microservices.constants.CategoryConstrants;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class CategoryController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	 
	@PostMapping(path = CategoryConstrants.CATEGORY_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Category create(@RequestBody Category category) throws URISyntaxException {
		return categoryService.create(category);
	}

	@RequestMapping(CategoryConstrants.CATEGORY_READ)
	Category read(@RequestParam Integer id) {
		return categoryService.read(id);
	}

	@RequestMapping(CategoryConstrants.CATEGORY_READ_ALL)
	List<Category> readAll() {
		return categoryService.readAll();
	}

	@PostMapping(path = CategoryConstrants.CATEGORY_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Category update(@RequestBody Category category) throws URISyntaxException {
		return categoryService.update(category);
	}

	@RequestMapping(CategoryConstrants.CATEGORY_DELETE)
	void delete(@RequestParam Integer id) {
		categoryService.delete(id);
	}
}