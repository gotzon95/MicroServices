package com.certiorem.microservices.DatabaseService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiorem.microservices.DatabaseService.repositories.CategoryRepository;
import com.certiorem.microservices.DatabaseService.service.CategoryService;
import com.certiorem.microservices.ModelDataService.Category;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category findByName(String name) {
		return categoryRepository.findByNombre(name);
	}
	
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}
	
	public Category findById(Integer id) {
		return categoryRepository.getOne(id);
	}

	public Category save(Category category) {
		
		return categoryRepository.save(category);
	}

	public void delete(Integer id) {
		categoryRepository.deleteById(id);
	}

}
