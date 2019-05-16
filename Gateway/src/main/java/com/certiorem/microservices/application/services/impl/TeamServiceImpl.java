package com.certiorem.microservices.application.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.application.services.TeamService;
import com.certiorem.microservices.constants.TeamConstrants;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public Team create(Team team) throws URISyntaxException {
		String baseUrl = TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result =restTemplate.postForEntity(uri, team, Team.class);

		return result.getBody();
	}

	@Override
	public Team read(Integer teamId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", teamId);
		
		ResponseEntity<Team> responseEntity = restTemplate.getForEntity(TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_READ + "?id={" + TeamConstrants.TEAM_SEARCH_PARAM + "}",
				Team.class, uriVariables);

		return responseEntity.getBody();
	}

	@Override
	public List<Team> readAll() {
		ResponseEntity<List<Team>> response = restTemplate.exchange(TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_READ_ALL,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>() {
				});

		return response.getBody();
	}

	@Override
	public void delete(Integer teamId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", teamId);
		
		restTemplate.delete(TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_DELETE + "?id={" + TeamConstrants.TEAM_SEARCH_PARAM + "}", uriVariables);
	}

	@Override
	public Team update(Team team) throws URISyntaxException {
		String baseUrl = TeamConstrants.TEAM_SERVICE_CONTROLLER_HOST + TeamConstrants.TEAM_UPDATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = restTemplate.postForEntity(uri, team, Team.class);

		return result.getBody();
	}

}
