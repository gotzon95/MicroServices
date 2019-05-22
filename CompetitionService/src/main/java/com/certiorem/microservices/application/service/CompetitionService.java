package com.certiorem.microservices.application.service;

import java.net.URISyntaxException;
import java.util.List;

import com.certiorem.microservices.ModelDataService.Category;
import com.certiorem.microservices.ModelDataService.Competition;

public interface CompetitionService {
	
	public Competition create(Competition competition) throws URISyntaxException;
	
}
