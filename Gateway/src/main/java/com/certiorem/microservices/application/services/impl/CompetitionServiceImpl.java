package com.certiorem.microservices.application.services.impl;

import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.certiorem.microservices.ModelDataService.Competition;
import com.certiorem.microservices.application.services.CompetitionService;

@Service
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    private RestTemplate restTemplate;

	@Override
	public Competition create(Competition Competition) throws URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

	
    
	

}
