package com.certiorem.microservices.application.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.application.service.CompetitionService;
import com.certiorem.microservices.constants.CompetitionConstrants;

@RestController
class CompetitionServiceController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionServiceController.class);

	@Autowired
	private CompetitionService competitionService;

	@PostMapping(value = CompetitionConstrants.COMPETITION_CREATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	Competition create(@RequestBody Competition competition) throws URISyntaxException {
		System.out.println("Competition" + competition);
		return competitionService.create(competition);
	}
}