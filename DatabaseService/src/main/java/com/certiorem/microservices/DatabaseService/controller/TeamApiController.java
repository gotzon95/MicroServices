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

@RestController
class TeamApiController {

	public static final Logger logger = LoggerFactory.getLogger(TeamApiController.class);

	@Autowired
	private TeamService teamService;

	@GetMapping("/team")
	Team showTeam(@RequestParam String name) {
		return getTeamInfo(name);
	}
	
	@RequestMapping("/team/readAllTeams")
	List<Team> showAllTeams() {
		List<Team> teams = teamService.findAllTeams();
		return teams;
	}

	@DeleteMapping("/team/deleteItem")
	void deleteTm(@RequestParam Integer id) {
		deleteTeam(id);
	}

	private Team getTeamInfo(String name) {
		System.out.println(name);
		Team team = teamService.findByName(name);
		System.out.println(team);
		return team;
	}
	
	private Team createTm(Team team) {
		System.out.println("JON - createTeam, team: " + team);
		return teamService.save(team);
	}

	@PostMapping("/team/createItem")
	Team createTeam(@RequestBody Team team) {
		return createTm(team);
	}
	
	private void deleteTeam(@PathVariable Integer id) {
		teamService.delete(id);
		
		System.out.println("Deleted");
	}
	
	@PostMapping("/team/updateItem")
	Team updateTeam(@RequestBody Team team) {
		return createTeam(team);
	}
}