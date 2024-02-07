package com.example.data_pipeline.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CountriesDataCSV implements Serializable {
    private String country;
    private String region;
    private Integer population;
    private Integer area;
    private String pop_density;
    private String coastline;
    private String net_migration;
    private String infant_mortality;
    private String gdp;
    private String literacy;
    private String phones;
    private String arable;
    private String crops;
    private String other;
    private String climate;
    private String birthrate;
    private String deathrate;
    private String agriculture;
    private String industry;
    private String service;
    private LocalDate extracted_at;
}
