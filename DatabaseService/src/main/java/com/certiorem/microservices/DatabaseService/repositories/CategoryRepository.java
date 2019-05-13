package com.certiorem.microservices.DatabaseService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certiorem.microservices.ModelDataService.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByNombre(String name);

}