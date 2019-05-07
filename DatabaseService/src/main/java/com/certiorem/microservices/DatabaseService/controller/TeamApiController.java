package com.certiorem.microservices.DatabaseService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.TeamService;
import com.certiorem.microservices.ModelDataService.Team;

@RestController
class TeamApiController {

	public static final Logger logger = LoggerFactory.getLogger(TeamApiController.class);

	
	@Autowired
	private TeamService teamService;

	
	@RequestMapping("/team/{name}")
	Team showTeam(@PathVariable String name) {

		return getTeamInfo(name);
	}

	private Team getTeamInfo(String number) {
		System.out.println(number);
		Team team = teamService.findByName(number);
		System.out.println(team);
		
		return team;
	}
}