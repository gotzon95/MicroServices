package com.certiorem.microservices.application.services;

import java.net.URISyntaxException;
import java.util.List;

import com.certiorem.microservices.ModelDataService.Driver;


public interface DriverService {
	public Driver create(Driver driver) throws URISyntaxException;
	
	public Driver read(Integer driverId);
	
	public List<Driver> readAll();
	
	public void delete(Integer driverId);
	
	public Driver update(Driver driver) throws URISyntaxException;
}
