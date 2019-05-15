package com.certiorem.microservices.DatabaseService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certiorem.microservices.ModelDataService.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {


}