package com.certiorem.microservices.application.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.certiorem.microservices.constants.CompetitionConstrants;
import com.certiorem.microservices.constants.URLConstants;

@Service
public class CompetitionServiceImpl implements CompetitionService {
	
    @Autowired
    private RestTemplate restTemplate;
    
	@Override
	public List<Competition> createCompetition() throws URISyntaxException {
		ResponseEntity<List<Category>> response = restTemplate.exchange(
				CategoryConstrants.DBB_CONNECTOR_CALL_CATEGORY, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Category>>() {
				});

		List<Category> categories = response.getBody();
		List<Competition> competitions = new ArrayList<Competition>();
		Competition competition = null;
		for (Category category: categories) {
			competition = new Competition();
			
			competition.setNombre(category.getNombre());
			competition.setResponsable(category.getNombre() + "moto");
			competition.setAño(LocalDate.now().getYear());
			competitions.add(create(competition));
		}
		return competitions;
	}
    
	private Competition create(Competition competition) throws URISyntaxException {
		String baseUrl = URLConstants.DDBB_SERVICE_CONTROLLER_HOST+ CompetitionConstrants.COMPETITION_CLASS_PARAM +CompetitionConstrants.COMPETITION_MICROSERVICE_CREATE;
		URI uri = new URI(baseUrl);

		ResponseEntity<Competition> result = restTemplate.postForEntity(uri, competition, Competition.class);

		return result.getBody();
	}

}
