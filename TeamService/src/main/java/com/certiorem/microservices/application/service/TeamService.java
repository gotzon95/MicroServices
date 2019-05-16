package com.certiorem.microservices.application.service;

import java.net.URISyntaxException;
import java.util.List;

import com.certiorem.microservices.ModelDataService.Team;

public interface TeamService {

	public Team readTeam(Integer teamId);
	
	public List<Team> readTeams();
	
	public Team create(Team team) throws URISyntaxException;
	
	public void delete(Integer teamId);
	
}
