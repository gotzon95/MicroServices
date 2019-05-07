package com.certiorem.microservices.DatabaseService.service;

import java.util.List;
import com.certiorem.microservices.ModelDataService.Driver;

public interface DriverService {
	
	Driver findByName(String name);

	List<Driver> findAllDrivers();
	
	Driver findById(Long id);

	Driver save(Driver driver);

	void delete(Long id);
}
