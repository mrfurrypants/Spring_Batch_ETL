package com.example.data_pipeline.config;

import com.example.data_pipeline.entity.CountriesDataDB;
import com.example.data_pipeline.model.CountriesDataCSV;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CountriesDataProcessor implements ItemProcessor<CountriesDataCSV, CountriesDataDB> {

    @Override
    public CountriesDataDB process(CountriesDataCSV csv) throws Exception {
        CountriesDataDB db = new CountriesDataDB();
        String stringValue;
        BigDecimal decimalValue;
        Integer integer;

        /**/
        stringValue = csv.getCountry();
        if (stringValue != null && !stringValue.isEmpty()) {
        db.setCountry(stringValue);
        } else {
            db.setCountry(null);
        }

        stringValue = csv.getRegion();
        if (stringValue != null && !stringValue.isEmpty()) {
        db.setRegion(stringValue);
        } else {
            db.setRegion(null);
        }
        /**/
        integer = csv.getPopulation();
        if (integer != null) {
        db.setPopulation(integer);
        } else {
            db.setPopulation(null);
        }

        integer = csv.getArea();
        if (integer != null) {
        db.setArea(integer);
        } else {
            db.setArea(null);
        }
        /**/
        stringValue = csv.getPop_density();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setPop_density(decimalValue);
        } else {
            db.setPop_density(null);
        }

        stringValue = csv.getCoastline();
        if (stringValue != null && !stringValue.isEmpty()) {
            stringValue = stringValue.replace(",", ".");
            decimalValue = new BigDecimal(stringValue);
            db.setCoastline(decimalValue);
        } else {
            db.setCoastline(null);
        }

        stringValue = csv.getNet_migration();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setNet_migration(decimalValue);
        } else {
            db.setNet_migration(null);
        }

        stringValue = csv.getInfant_mortality();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setInfant_mortality(decimalValue);
        } else {
            db.setInfant_mortality(null);
        }
        /**/
        stringValue = csv.getGdp();
        if (stringValue != null && !stringValue.isEmpty()) {
        db.setGdp(Integer.valueOf(stringValue));
        } else {
            db.setGdp(null);
        }
        /**/
        stringValue = csv.getLiteracy();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setLiteracy(decimalValue);
        } else {
            db.setLiteracy(null);
        }

        stringValue = csv.getPhones();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setPhones(decimalValue);
        } else {
            db.setPhones(null);
        }

        stringValue = csv.getArable();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setArable(decimalValue);
        } else {
            db.setArable(null);
        }

        stringValue = csv.getCrops();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setCrops(decimalValue);
        } else {
            db.setCrops(null);
        }

        stringValue = csv.getOther();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setOther(decimalValue);
        } else {
            db.setOther(null);
        }

        stringValue = csv.getClimate();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setClimate(decimalValue);
        } else {
            db.setClimate(null);
        }

        stringValue = csv.getBirthrate();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setBirthrate(decimalValue);
        } else {
            db.setBirthrate(null);
        }

        stringValue = csv.getDeathrate();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setDeathrate(decimalValue);
        } else {
            db.setDeathrate(null);
        }

        stringValue = csv.getAgriculture();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setAgriculture(decimalValue);
        } else {
            db.setAgriculture(null);
        }

        stringValue = csv.getIndustry();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setIndustry(decimalValue);
        } else {
            db.setIndustry(null);
        }

        stringValue = csv.getService();
        if (stringValue != null && !stringValue.isEmpty()) {
        stringValue = stringValue.replace(",", ".");
        decimalValue = new BigDecimal(stringValue);
        db.setService(decimalValue);
        } else {
            db.setService(null);
        }

        return db;
    }
}