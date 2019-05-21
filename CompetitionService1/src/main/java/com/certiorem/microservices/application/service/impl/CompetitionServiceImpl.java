package com.certiorem.microservices.application.service.impl;

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

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.application.service.CompetitionService;
import com.certiorem.microservices.constants.CategoryConstrants;

@Service
public class CompetitionServiceImpl implements CompetitionService {
	
    @Autowired
    private RestTemplate restTemplate;
    
	@Override
	public Competition readCompetition(Integer competitionId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", competitionId);

		ResponseEntity<Competition> responseEntity = restTemplate.getForEntity("http://localhost:8094/competition?id={id}", Competition.class, uriVariables);
		return responseEntity.getBody();
	}

	@Override
	public List<Competition> readCompetitions() {
		ResponseEntity<List<Competition>> response = restTemplate.exchange(
				"http://localhost:8094/competition/readAllCompetitions", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Competition>>() {
				});

		return response.getBody();
	}

	@Override
	public void delete(Integer competitionId) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", competitionId);

		restTemplate.delete("http://localhost:8094/competition/deleteItem?id={id}", uriVariables);
	}

	@Override
	public Competition create(Competition competition) throws URISyntaxException {
		String baseUrl = "http://localhost:8094/competition/createItem";
		URI uri = new URI(baseUrl);

		ResponseEntity<Competition> result = restTemplate.postForEntity(uri, competition, Competition.class);

		return result.getBody();
	}

	
}
