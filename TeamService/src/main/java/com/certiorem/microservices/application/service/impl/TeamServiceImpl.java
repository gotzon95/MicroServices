package com.certiorem.microservices.application.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.application.service.TeamService;
import com.certiorem.microservices.constants.TeamConstrants;

@Service
public class TeamServiceImpl implements TeamService{

	@Override
	public Team readTeam(Integer teamId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", teamId);

		ResponseEntity<Team> responseEntity = new RestTemplate().getForEntity(TeamConstrants.TEAM_SERVICE_GET_TEAM,
				Team.class, uriVariables);
		return responseEntity.getBody();
	}

	@Override
	public List<Team> readTeams() {
		ResponseEntity<List<Team>> response = new RestTemplate().exchange(TeamConstrants.DBB_CONNECTOR_CALL_TEAM,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>() {
				});

		return response.getBody();
	}

	@Override
	public Team create(Team team) throws URISyntaxException {
		String baseUrl = TeamConstrants.DBB_CONNECTOR_CREATE_TEAM;
		URI uri = new URI(baseUrl);

		ResponseEntity<Team> result = new RestTemplate().postForEntity(uri, team, Team.class);

		return result.getBody();
	}

	@Override
	public void delete(Integer teamId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", teamId);

		new RestTemplate().delete(TeamConstrants.DBB_CONNECTOR_DELETE_TEAM, uriVariables);
	}
}
