package com.certiorem.microservices.DatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.certiorem.microservices.DatabaseService.repositories.CompetitionRepository;
import com.certiorem.microservices.DatabaseService.service.CompetitionService;
import com.certiorem.microservices.ModelDataService.Competition;


@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;

	public Competition save(Competition competition) {
		return competitionRepository.save(competition);
	}

}
