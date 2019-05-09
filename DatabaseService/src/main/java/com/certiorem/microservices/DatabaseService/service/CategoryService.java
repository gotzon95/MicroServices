package com.certiorem.microservices.DatabaseService.service;

import java.util.List;
import com.certiorem.microservices.ModelDataService.Category;

public interface CategoryService {
	
	Category findByName(String name);
	
	List<Category> findAllCategories();
	
	Category findById(Integer id);

	Category save(Category category);

	void delete(Integer id);

}
