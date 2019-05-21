package com.certiorem.microservices.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.application.services.CompetitionService;
import com.certiorem.microservices.constants.CompetitionConstrants;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class CompetitionController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);
	
	@Autowired
	private CompetitionService competitionService;
	
	@PostMapping(path = CompetitionConstrants.COMPETITION_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	Competition create(@RequestBody Competition competition) throws URISyntaxException {
		return competitionService.create(competition);
	}
	
	
}