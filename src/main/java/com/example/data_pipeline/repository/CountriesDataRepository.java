package com.example.data_pipeline.repository;

import com.example.data_pipeline.entity.CountriesDataDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesDataRepository extends JpaRepository<CountriesDataDB, Integer> {

}
