package com.certiorem.microservices.application.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.application.service.CompetitionService;
import com.certiorem.microservices.constants.CompetitionConstrants;

@RestController
class CompetitionServiceController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionServiceController.class);

	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping(value = CompetitionConstrants.COMPETITION_CREATE_NEW, produces = "application/json")
	public List<Competition> createCompetition() throws URISyntaxException {
		return competitionService.createCompetition();
	}
}