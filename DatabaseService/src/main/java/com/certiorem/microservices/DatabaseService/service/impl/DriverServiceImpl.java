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
	
	public Driver findByName(String name) {
		return driverRepository.findByName(name);
	}
	
	public List<Driver> findAllDrivers() {
		return driverRepository.findAll();
	}

	public Driver findById(Long id) {
		return driverRepository.getOne(id);
	}



}
