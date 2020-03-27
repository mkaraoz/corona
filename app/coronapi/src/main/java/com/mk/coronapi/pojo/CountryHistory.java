package com.mk.coronapi.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryHistory {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("stat_by_country")
    @Expose
    private List<StatByCountry> statByCountry = null;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<StatByCountry> getStatByCountry() {
        return statByCountry;
    }

    public void setStatByCountry(List<StatByCountry> statByCountry) {
        this.statByCountry = statByCountry;
    }

}
