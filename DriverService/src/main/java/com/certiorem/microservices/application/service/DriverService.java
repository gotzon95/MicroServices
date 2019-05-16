package com.certiorem.microservices.application.service;

import java.net.URISyntaxException;
import java.util.List;

import com.certiorem.microservices.ModelDataService.Driver;

public interface DriverService {

	public Driver readDriver(Integer driverId);
	
	public List<Driver> readDrivers();
	
	public Driver create(Driver driver) throws URISyntaxException;
	
	public void delete(Integer driverId);
	
}
