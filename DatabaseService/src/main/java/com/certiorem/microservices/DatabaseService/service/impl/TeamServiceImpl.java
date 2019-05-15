package com.certiorem.microservices.DatabaseService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiorem.microservices.DatabaseService.repositories.TeamRepository;
import com.certiorem.microservices.DatabaseService.service.TeamService;
import com.certiorem.microservices.ModelDataService.Team;


@Service
@Transactional
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public List<Team> findAllTeams() {
		return teamRepository.findAll();
	}

	public Team findById(Integer id) {
		return teamRepository.getOne(id);
	}

	public Team save(Team team) {
		return teamRepository.save(team);
	}

	public void delete(Integer id) {
		teamRepository.deleteById(id);
	}


}
