package com.certiorem.microservices.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.application.service.TeamService;
import com.certiorem.microservices.constants.TeamConstrants;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class TeamServiceController {

	public static final Logger logger = LoggerFactory.getLogger(TeamServiceController.class);
	
	@Autowired
	private TeamService teamService;
	 
	@RequestMapping(value = TeamConstrants.TEAM_READ)
	@ResponseBody
	public Team readTeam(@RequestParam Integer id) {
		return teamService.readTeam(id);
	}
	
	@RequestMapping(value = TeamConstrants.TEAM_READ_ALL)
	@ResponseBody
	public List<Team> readTeams() {
		return teamService.readTeams();
	}
	
	@RequestMapping(value = TeamConstrants.TEAM_DELETE)
	@ResponseBody
	void delete(@RequestParam Integer id) {
		teamService.delete(id);
	}
	
	@PostMapping(path = TeamConstrants.TEAM_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Team create(@RequestBody Team team) throws URISyntaxException {
		return teamService.create(team);
	}
	
	
}