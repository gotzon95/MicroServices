package com.certiorem.microservices.DatabaseService.service;

import java.util.List;

import com.certiorem.microservices.ModelDataService.Team;

public interface TeamService {
	
	Team findByName(String number);
	
	List<Team> findAllTeams();
	
	Team findById(Long id);

}
