package com.certiorem.microservices.Gateway.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Team;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController

class TeamController {

	public static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@PostMapping(path = "/team/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Team create(@RequestBody Team team) throws URISyntaxException {

		System.out.println("XAVI - team1: " + team);
		
		String baseUrl = "http://localhost:8090/team/create";
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = new RestTemplate().postForEntity(uri, team, Team.class);

		return result.getBody();
	}

	@RequestMapping("team/read")
	Team read(@RequestParam String name) {

		System.out.println("Gotzon - Gateway id: " + name);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("name", name);

		ResponseEntity<Team> responseEntity = new RestTemplate().getForEntity("http://localhost:8090/team/read?name={name}",
				Team.class, uriVariables);

		return responseEntity.getBody();
	}

	@RequestMapping("team/readAll")
	List<Team> read() {
		
		ResponseEntity<List<Team>> response = new RestTemplate().exchange("http://localhost:8090/team/readTeams",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>() {
				});

		return response.getBody();
	}

	@PostMapping(path = "/team/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Team update(@RequestBody Team team) throws URISyntaxException {

		System.out.println("XAVI - team: " + team);
		
		String baseUrl = "http://localhost:8090/team/update";
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = new RestTemplate().postForEntity(uri, team, Team.class);

		return result.getBody();
	}

	@RequestMapping("team/delete")
	Team delete(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);

		ResponseEntity<Team> responseEntity = new RestTemplate().getForEntity("http://localhost:8090/team/deleteTeam?id={id}",
				Team.class, uriVariables);

		return responseEntity.getBody();
	}

}