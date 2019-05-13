package com.certiorem.microservices.application.services;

import java.net.URISyntaxException;
import java.util.List;


import com.certiorem.microservices.ModelDataService.Team;


public interface TeamService {
	public Team create(Team team) throws URISyntaxException;
	
	public Team read(Integer teamId);
	
	public List<Team> readAll();
	
	public void delete(Integer teamId);
	
	public Team update(Team team) throws URISyntaxException;
}
