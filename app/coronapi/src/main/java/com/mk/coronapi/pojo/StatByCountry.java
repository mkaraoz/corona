package com.mk.coronapi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatByCountry {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("total_cases")
    @Expose
    private String totalCases;
    @SerializedName("new_cases")
    @Expose
    private String newCases;
    @SerializedName("active_cases")
    @Expose
    private String activeCases;
    @SerializedName("total_deaths")
    @Expose
    private String totalDeaths;
    @SerializedName("new_deaths")
    @Expose
    private String newDeaths;
    @SerializedName("total_recovered")
    @Expose
    private String totalRecovered;
    @SerializedName("serious_critical")
    @Expose
    private String seriousCritical;
    @SerializedName("region")
    @Expose
    private Object region;
    @SerializedName("total_cases_per1m")
    @Expose
    private String totalCasesPer1m;
    @SerializedName("record_date")
    @Expose
    private String recordDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getSeriousCritical() {
        return seriousCritical;
    }

    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
    }

    public String getTotalCasesPer1m() {
        return totalCasesPer1m;
    }

    public void setTotalCasesPer1m(String totalCasesPer1m) {
        this.totalCasesPer1m = totalCasesPer1m;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

}
