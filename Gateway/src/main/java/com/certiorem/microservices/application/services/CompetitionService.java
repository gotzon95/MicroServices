package com.certiorem.microservices.application.services;

import java.net.URISyntaxException;
import com.certiorem.microservices.ModelDataService.Competition;


public interface CompetitionService {

	public Competition create(Competition competition) throws URISyntaxException;
	public Competition newCompetition() throws URISyntaxException;

}
