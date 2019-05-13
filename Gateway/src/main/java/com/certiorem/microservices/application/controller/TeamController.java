package com.certiorem.microservices.Gateway.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

class TeamController {

	public static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@PostMapping(path = TeamConstrants.TEAM_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Team create(@RequestBody Team team) throws URISyntaxException {

		System.out.println("XAVI - team1: " + team);
		
		String baseUrl = TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = new RestTemplate().postForEntity(uri, team, Team.class);

		return result.getBody();
	}

	@RequestMapping(TeamConstrants.TEAM_READ)
	Team read(@RequestParam String id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("id", id);
		
		ResponseEntity<Team> responseEntity = new RestTemplate().getForEntity(TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_READ + "?id={" + TeamConstrants.TEAM_SEARCH_PARAM + "}",
				Team.class, uriVariables);

		return responseEntity.getBody();
	}

	@RequestMapping(TeamConstrants.TEAM_READ_ALL)
	List<Team> read() {
		
		ResponseEntity<List<Team>> response = new RestTemplate().exchange(TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_READ_ALL,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>() {
				});

		return response.getBody();
	}

	@PostMapping(path = TeamConstrants.TEAM_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Team update(@RequestBody Team team) throws URISyntaxException {

		System.out.println("XAVI - team: " + team);
		
		String baseUrl = TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_UPDATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = new RestTemplate().postForEntity(uri, team, Team.class);

		return result.getBody();
	}

	@DeleteMapping(TeamConstrants.TEAM_DELETE)
	void delete(@RequestParam Integer id) {

		System.out.println("Gotzon - Gateway id: " + id);

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);
		
		new RestTemplate().delete(TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_DELETE + "?id={" + TeamConstrants.TEAM_SEARCH_PARAM + "}", uriVariables);
	}

}