package com.example.data_pipeline.repository;

import com.example.data_pipeline.entity.CovidDataDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidDataRepository extends JpaRepository<CovidDataDB, Integer> {

}
