package com.mk.coronapi.api;

import com.mk.coronapi.pojo.Cases;
import com.mk.coronapi.pojo.Countries;
import com.mk.coronapi.pojo.CountryHistory;
import com.mk.coronapi.pojo.WorldStats;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CoronaService {
    @Headers({
            "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com",
            "x-rapidapi-key: 5c049c2128msha28ff1a88ed9ccap1a394ajsnded1c7250398"
    })
    @GET("affected.php")
    Call<Countries> getCountries();

    @Headers({
            "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com",
            "x-rapidapi-key: 5c049c2128msha28ff1a88ed9ccap1a394ajsnded1c7250398"
    })
    @GET("cases_by_country.php")
    Call<Cases> getCases();

    @Headers({
            "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com",
            "x-rapidapi-key: 5c049c2128msha28ff1a88ed9ccap1a394ajsnded1c7250398"
    })
    @GET("worldstat.php")
    Call<WorldStats> getStats();

    @Headers({
            "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com",
            "x-rapidapi-key: 5c049c2128msha28ff1a88ed9ccap1a394ajsnded1c7250398"
    })
    @GET("cases_by_particular_country.php")
    Call<CountryHistory> getHistoryByCountry(@Query("country")String name);
}
