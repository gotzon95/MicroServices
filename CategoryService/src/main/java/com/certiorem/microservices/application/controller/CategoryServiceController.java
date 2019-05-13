package com.certiorem.microservices.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.application.service.CategoryService;
import com.certiorem.microservices.constants.CategoryConstrants;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class CategoryServiceController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryServiceController.class);

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = CategoryConstrants.CATEGORY_READ)
	@ResponseBody
	public Category readCategory(@RequestParam Integer id) {
		return categoryService.readCategory(id);
	}

	@RequestMapping(value = CategoryConstrants.CATEGORY_READ_ALL)
	@ResponseBody
	public List<Category> readCategories() {
		return categoryService.readCategories();
	}

	@DeleteMapping(CategoryConstrants.CATEGORY_DELETE)
	@ResponseBody
	void delete(@RequestParam Integer id) {
		categoryService.delete(id);
	}

	@PostMapping(path = CategoryConstrants.CATEGORY_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Category create(@RequestBody Category category) throws URISyntaxException {
		return categoryService.create(category);
	}
}