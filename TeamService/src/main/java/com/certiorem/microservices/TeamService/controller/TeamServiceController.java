package com.certiorem.microservices.TeamService.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.constants.TeamConstrants;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class TeamServiceController {

	public static final Logger logger = LoggerFactory.getLogger(TeamServiceController.class);
	 
	@RequestMapping(value = TeamConstrants.TEAM_READ)
	@ResponseBody
	public Team readTeam(@RequestParam Integer id) {
		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		ResponseEntity<Team> responseEntity = new RestTemplate().getForEntity(TeamConstrants.TEAM_SERVICE_GET_TEAM,
				Team.class, uriVariables);
		return responseEntity.getBody();
	}
	
	@RequestMapping(value = TeamConstrants.TEAM_READ_ALL)
	@ResponseBody
	public List<Team> readTeams() {
		ResponseEntity<List<Team>> response = new RestTemplate().exchange(TeamConstrants.DBB_CONNECTOR_CALL_TEAM,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>() {
				});

		return response.getBody();
	}
	
	@RequestMapping(value = TeamConstrants.TEAM_DELETE)
	@ResponseBody
	void delete(@RequestParam Integer id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		new RestTemplate().delete(TeamConstrants.DBB_CONNECTOR_DELETE_TEAM, uriVariables);

	}
	

	
	@PostMapping(path = TeamConstrants.TEAM_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Team create(@RequestBody Team team) throws URISyntaxException {

		System.out.println("XAVI - team: " + team);
		
		String baseUrl = TeamConstrants.DBB_CONNECTOR_CREATE_TEAM;
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = new RestTemplate().postForEntity(uri, team, Team.class);

		return result.getBody();
	}
	
	
}