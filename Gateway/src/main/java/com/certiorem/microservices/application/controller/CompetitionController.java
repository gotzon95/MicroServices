package com.certiorem.microservices.application.controller;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.application.services.CompetitionService;
import com.certiorem.microservices.constants.CompetitionConstrants;

@RestController
class CompetitionController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);
	
	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping(path = CompetitionConstrants.COMPETITION_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Competition create() throws URISyntaxException {
		return competitionService.newCompetition();
	}
	
}