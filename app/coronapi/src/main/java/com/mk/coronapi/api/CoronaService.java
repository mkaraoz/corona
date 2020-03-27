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
            "x-rapidapi-key: 9b7c30b76emsh7d908d014bffc98p111093jsn48aaccdbc06c"
    })
    @GET("affected.php")
    Call<Countries> getCountries();

    @Headers({
            "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com",
            "x-rapidapi-key: 9b7c30b76emsh7d908d014bffc98p111093jsn48aaccdbc06c"
    })
    @GET("cases_by_country.php")
    Call<Cases> getCases();

    @Headers({
            "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com",
            "x-rapidapi-key: 9b7c30b76emsh7d908d014bffc98p111093jsn48aaccdbc06c"
    })
    @GET("worldstat.php")
    Call<WorldStats> getStats();

    @Headers({
            "x-rapidapi-host: coronavirus-monitor.p.rapidapi.com",
            "x-rapidapi-key: 9b7c30b76emsh7d908d014bffc98p111093jsn48aaccdbc06c"
    })
    @GET("cases_by_particular_country.php")
    Call<CountryHistory> getHistoryByCountry(@Query("country")String name);
}
