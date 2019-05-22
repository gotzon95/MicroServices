package com.certiorem.microservices.DatabaseService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.CompetitionService;
import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.constants.CompetitionConstrants;

@RestController
class CompetitionApiController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionApiController.class);
	
	@Autowired
	private CompetitionService competitionService;

	@PostMapping(CompetitionConstrants.COMPETITION_CLASS_PARAM + CompetitionConstrants.COMPETITION_MICROSERVICE_CREATE)
	@ResponseBody
	Competition createComp(@RequestBody Competition competition) {
		System.out.println("Competition" + competition);
		return competitionService.save(competition);
	}
	
	
	
	

}