package com.mk.coronavirus.db.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "cases", indices = {@Index(value = {"name"}, unique = true)})
public class CaseByCountry {

    public static String getOrder(String o) {
        switch (o) {
            case "0":
                return SORT_BY_DEATH_COUNT;
            case "1":
                return SORT_BY_CASE_COUNT;
            case "2":
                return SORT_BY_NAME;
            default:
                return SORT_BY_DEATH_COUNT;
        }
    }

    public static final String SORT_BY_DEATH_COUNT = "0";
    public static final String SORT_BY_CASE_COUNT = "1";
    public static final String SORT_BY_NAME = "2";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int caseCount;
    private int deaths;
    private int recovered;
    private int activeCases;
    private int casePerOneMillion;

    public CaseByCountry(int id, String name, int caseCount, int deaths, int recovered, int activeCases, int casePerOneMillion) {
        this.id = id;
        this.name = name;
        this.caseCount = caseCount;
        this.deaths = deaths;
        this.recovered = recovered;
        this.activeCases = activeCases;
        this.casePerOneMillion = casePerOneMillion;
    }

    @Ignore
    public CaseByCountry(String name, int caseCount, int deaths, int recovered, int activeCases, int casePerOneMillion) {
        this.name = name;
        this.caseCount = caseCount;
        this.deaths = deaths;
        this.recovered = recovered;
        this.activeCases = activeCases;
        this.casePerOneMillion = casePerOneMillion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public int getCasePerOneMillion() {
        return casePerOneMillion;
    }

    public void setCasePerOneMillion(int casePerOneMillion) {
        this.casePerOneMillion = casePerOneMillion;
    }
}
