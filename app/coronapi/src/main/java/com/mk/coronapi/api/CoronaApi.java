package com.mk.coronapi.api;

import com.mk.coronapi.pojo.Cases;
import com.mk.coronapi.pojo.Countries;
import com.mk.coronapi.pojo.CountryHistory;
import com.mk.coronapi.pojo.WorldStats;

import java.io.IOException;

public interface CoronaApi {
    Result<Countries> pullCountries() throws IOException;
    void getCountries(final CoronaApiCallback<Countries> userCallback);

    Result<Cases> pullCases() throws IOException;
    void getCases(final CoronaApiCallback<Cases> userCallback);

    Result<WorldStats> pullStats() throws IOException;
    void getStats(final CoronaApiCallback<WorldStats> userCallback);

    Result<CountryHistory> pullHistoryByCountry(String name) throws IOException;
    void getHistoryByCountry(String name, final CoronaApiCallback<CountryHistory> userCallback);
}
