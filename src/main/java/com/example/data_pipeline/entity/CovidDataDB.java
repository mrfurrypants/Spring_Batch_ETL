package com.example.data_pipeline.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "covid_data", uniqueConstraints = {@UniqueConstraint(columnNames = {"country", "indicator", "year_week"})})

public class CovidDataDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String country_code;
    private String continent;
    private Integer population;
    private String indicator;
    private Integer weekly_count;
    private String year_week;
    @Column(precision = 20, scale = 10)
    private BigDecimal rate_14_day;
    private Integer cumulative_count;
    private String source;
    private String note;
}
