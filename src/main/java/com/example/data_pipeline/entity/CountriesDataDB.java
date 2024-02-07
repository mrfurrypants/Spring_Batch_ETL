package com.example.data_pipeline.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity/*JPA annotation used to map Java objects to database tables*/
@Table(name = "countries_data")
public class CountriesDataDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String region;
    private Integer population;
    private Integer area;
    @Column(precision = 10, scale = 3)
    private BigDecimal pop_density;
    @Column(precision = 10, scale = 3)
    private BigDecimal coastline;
    @Column(precision = 10, scale = 3)
    private BigDecimal net_migration;
    @Column(precision = 10, scale = 3)
    private BigDecimal infant_mortality;
    private Integer gdp;
    @Column(precision = 10, scale = 3)
    private BigDecimal literacy;
    @Column(precision = 10, scale = 3)
    private BigDecimal phones;
    @Column(precision = 10, scale = 3)
    private BigDecimal arable;
    @Column(precision = 10, scale = 3)
    private BigDecimal crops;
    @Column(precision = 10, scale = 3)
    private BigDecimal other;
    @Column(precision = 10, scale = 3)
    private BigDecimal climate;
    @Column(precision = 10, scale = 3)
    private BigDecimal birthrate;
    @Column(precision = 10, scale = 3)
    private BigDecimal deathrate;
    @Column(precision = 10, scale = 3)
    private BigDecimal agriculture;
    @Column(precision = 10, scale = 3)
    private BigDecimal industry;
    @Column(precision = 10, scale = 3)
    private BigDecimal service;
}