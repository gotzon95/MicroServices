package com.certiorem.microservices.DatabaseService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certiorem.microservices.ModelDataService.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findByNombre(String name);

}