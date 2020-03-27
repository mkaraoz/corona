package com.mk.coronapi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Countries {

    @SerializedName("affected_countries")
    @Expose
    private List<String> affectedCountries = null;

    @SerializedName("statistic_taken_at")
    @Expose
    private String statisticTakenAt;

    public List<String> getAffectedCountries() {
        return affectedCountries;
    }

    public void setAffectedCountries(List<String> affectedCountries) {
        this.affectedCountries = affectedCountries;
    }

    public String getStatisticTakenAt() {
        return statisticTakenAt;
    }

    public void setStatisticTakenAt(String statisticTakenAt) {
        this.statisticTakenAt = statisticTakenAt;
    }
}