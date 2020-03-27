package com.mk.coronavirus.db.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "history", indices = {@Index(value = {"name", "date"},
        unique = true)})
public class CountryHistoricalData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int totalCases;
    private int newCases;
    private int totalDeaths;
    private int newDeaths;
    private int totalRecovered;
    private String date;
    private long timeStamp;

    public CountryHistoricalData(int id, String name, int totalCases, int newCases, int totalDeaths, int newDeaths, int totalRecovered, String date, long timeStamp) {
        this.id = id;
        this.name = name;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.totalRecovered = totalRecovered;
        this.date = date;
        this.timeStamp = timeStamp;
    }

    @Ignore
    public CountryHistoricalData(String name, int totalCases, int newCases, int totalDeaths, int newDeaths, int totalRecovered, String date, long timeStamp) {
        this.name = name;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.totalRecovered = totalRecovered;
        this.date = date;
        this.timeStamp = timeStamp;
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

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
