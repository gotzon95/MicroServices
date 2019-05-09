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
import com.certiorem.microservices.constants.URLConstants;

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
	 
	@RequestMapping(value = "/team/read")
	@ResponseBody
	public Team readTeam(@RequestParam String name) {
		System.out.println("Gotzon - Gateway id: " + name);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("name", name);

		ResponseEntity<Team> responseEntity = new RestTemplate().getForEntity(URLConstants.TEAM_SERVIVE_GET_TEAM,
				Team.class, uriVariables);
		return responseEntity.getBody();
	}
	
	@RequestMapping(value = "/team/readTeams")
	@ResponseBody
	public List<Team> readTeams() {
		ResponseEntity<List<Team>> response = new RestTemplate().exchange("http://192.168.1.117:8093/team/readAllTeams",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>() {
				});

		return response.getBody();
	}
	
	@RequestMapping(value = "/team/deleteTeam")
	@ResponseBody
	void delete(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		new RestTemplate().delete("http://192.168.1.117:8093/team/deleteItem?id={id}", uriVariables);

	}
	

	
	@PostMapping(path = "/team/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Team create(@RequestBody Team team) throws URISyntaxException {

		System.out.println("XAVI - team: " + team);
		
		String baseUrl = "http://192.168.1.117:8093/team/createItem";
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = new RestTemplate().postForEntity(uri, team, Team.class);

		return result.getBody();
	}
	
	
}