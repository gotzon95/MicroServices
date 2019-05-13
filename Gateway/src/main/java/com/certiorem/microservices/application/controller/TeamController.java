package com.certiorem.microservices.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.application.services.TeamService;
import com.certiorem.microservices.constants.TeamConstrants;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController

class TeamController {

	public static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private TeamService teamService;

	@PostMapping(path = TeamConstrants.TEAM_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Team create(@RequestBody Team team) throws URISyntaxException {
		return teamService.create(team);
	}

	@RequestMapping(TeamConstrants.TEAM_READ)
	Team read(@RequestParam Integer id) {
		return teamService.read(id);
	}

	@RequestMapping(TeamConstrants.TEAM_READ_ALL)
	List<Team> readAll() {
		return teamService.readAll();
	}

	@PostMapping(path = TeamConstrants.TEAM_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Team update(@RequestBody Team team) throws URISyntaxException {
		return teamService.update(team);
	}

	@DeleteMapping(TeamConstrants.TEAM_DELETE)
	void delete(@RequestParam Integer id) {
		teamService.delete(id);
	}

}