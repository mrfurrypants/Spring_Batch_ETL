package com.example.data_pipeline.config;

import com.example.data_pipeline.entity.CovidDataDB;
import org.springframework.batch.item.ItemProcessor;

public class CovidDataProcessor implements ItemProcessor<CovidDataDB, CovidDataDB> {
    @Override
    public CovidDataDB process(CovidDataDB item) throws Exception {
        return item;
    }
}
