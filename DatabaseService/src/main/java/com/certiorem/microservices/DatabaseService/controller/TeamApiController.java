package com.certiorem.microservices.DatabaseService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.TeamService;
import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.constants.TeamConstrants;

@RestController
class TeamApiController {

	public static final Logger logger = LoggerFactory.getLogger(TeamApiController.class);

	@Autowired
	private TeamService teamService;

	@GetMapping(TeamConstrants.TEAM_CLASS_PARAM)
	Team showTeam(@RequestParam Integer id) {
		return getTeamInfo(id);
	}
	
	@RequestMapping(TeamConstrants.TEAM_CLASS_PARAM + TeamConstrants.TEAM_MICROSERVICE_READ)
	List<Team> showAllTeams() {
		List<Team> teams = teamService.findAllTeams();
		return teams;
	}

	@DeleteMapping(TeamConstrants.TEAM_CLASS_PARAM + TeamConstrants.TEAM_MICROSERVICE_DELETE)
	void deleteTm(@RequestParam Integer id) {
		deleteTeam(id);
	}

	private Team getTeamInfo(Integer id) {
		System.out.println(id);
		Team team = teamService.findById(id);
		System.out.println(team);
		return team;
	}
	
	private Team createTm(Team team) {
		System.out.println("JON - createTeam, team: " + team);
		return teamService.save(team);
	}

	@PostMapping(TeamConstrants.TEAM_CLASS_PARAM + TeamConstrants.TEAM_MICROSERVICE_CREATE)
	Team createTeam(@RequestBody Team team) {
		return createTm(team);
	}
	
	private void deleteTeam(@PathVariable Integer id) {
		teamService.delete(id);
		
		System.out.println("Deleted");
	}
	
	@PostMapping(TeamConstrants.TEAM_CLASS_PARAM + TeamConstrants.TEAM_MICROSERVICE_UPDATE)
	Team updateTeam(@RequestBody Team team) {
		return createTeam(team);
	}
}