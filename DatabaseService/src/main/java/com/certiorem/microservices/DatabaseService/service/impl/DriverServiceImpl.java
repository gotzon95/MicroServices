package com.certiorem.microservices.DatabaseService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiorem.microservices.DatabaseService.repositories.DriverRepository;
import com.certiorem.microservices.DatabaseService.service.DriverService;
import com.certiorem.microservices.ModelDataService.Driver;


@Service
@Transactional
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	public List<Driver> findAllDrivers() {
		return driverRepository.findAll();
	}

	public Driver findById(Integer id) {
		return driverRepository.getOne(id);
	}

	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}

	public void delete(Integer id) {
		driverRepository.deleteById(id);
	}



}
