package com.certiorem.microservices.DatabaseService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.certiorem.microservices.DatabaseService.service.CompetitionService;
import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.constants.CategoryConstrants;

@RestController
class CompetitionApiController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionApiController.class);
	
	@Autowired
	private CompetitionService competitionService;

	@PostMapping(CategoryConstrants.CATEGORY_CLASS_PARAM + CategoryConstrants.CATEGORY_MICROSERVICE_CREATE)
	Competition createComp(@RequestBody Competition competition) {
		return competitionService.save(competition);
	}
	
	

}