package com.certiorem.microservices.application.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.application.services.CompetitionService;
import com.certiorem.microservices.constants.CompetitionConstrants;

@Service
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    private RestTemplate restTemplate;

	@Override
	public Competition create(Competition competition) throws URISyntaxException {
		String baseUrl = CompetitionConstrants.COMPETITION_SERVICE_CONTROLLER_HOST + CompetitionConstrants.COMPETITION_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Competition> result = restTemplate.postForEntity(uri, competition, Competition.class);

		return result.getBody();
	}

	@Override
	public Competition newCompetition() throws URISyntaxException {
		String baseUrl = CompetitionConstrants.COMPETITION_SERVICE_CONTROLLER_HOST + CompetitionConstrants.COMPETITION_CREATE_NEW;
		URI uri = new URI(baseUrl);
		
		ResponseEntity<Competition> result = restTemplate.getForEntity(uri, Competition.class);
		
		return result.getBody();
	}

	
    
	

}
