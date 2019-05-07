package com.certiorem.microservices.DatabaseService.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/team/delete")
	void deleteTm(@PathVariable Long id) {
		deleteTeam(id);
	}

	private Team getTeamInfo(String number) {
		System.out.println(number);
		Team team = teamService.findByName(number);
		System.out.println(team);

		return team;
	}

	@RequestMapping("/team/create")
	private Team createTeam(@RequestBody Map<String, String> body) {
		String name = body.get("name");

		return teamService.save(new Team());
	}

	@RequestMapping("/team/update/{id}")
	public Team update(@PathVariable String id, @RequestBody Map<String, String> body) {
		Long teamId = Long.parseLong(id);
		
		Team team = teamService.findById(teamId);
		team.setNombre(body.get("name"));
		//team.setVictorias(body.get("victory"));
		return teamService.save(team);
	}
	
	private void deleteTeam(@PathVariable Long id) {
		teamService.delete(id);
		
		System.out.println("Deleted");
	}
}