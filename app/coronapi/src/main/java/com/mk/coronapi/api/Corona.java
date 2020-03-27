package com.mk.coronapi.api;

import androidx.annotation.NonNull;

import com.mk.coronapi.pojo.Cases;
import com.mk.coronapi.pojo.Countries;
import com.mk.coronapi.pojo.CountryHistory;
import com.mk.coronapi.pojo.WorldStats;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Corona implements CoronaApi {
    private static final String CORONA_API_URL = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/";
    //private static final String CORONA_API_URL = "http://192.168.0.16:8080/";

    private final CoronaService mCoronaApi;

    private Corona(CoronaService coronaApi) {
        mCoronaApi = coronaApi;
    }

    @NonNull
    public static Corona publicEndpoint() {
        Retrofit retrofit = RetrofitClient.getClient(CORONA_API_URL);
        return new Corona(retrofit.create(CoronaService.class));
    }

    private <T> Result<T> pull(Call<T> call) throws IOException {
        Response<T> response = call.execute();
        return new Result<>(response.code(), response.body());
    }

    private <T> void get(final Call<T> c, final CoronaApiCallback<T> userCallback) {
        c.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                T t = response.body();

                ApiResponse res = new ApiResponse(response.raw(), response.errorBody());
                userCallback.onResponse(t, res);
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {
                userCallback.onError(throwable);
            }
        });
    }

    //Countries
    @Override
    public Result<Countries> pullCountries() throws IOException {
        Call<Countries> call = mCoronaApi.getCountries();
        return pull(call);
    }

    @Override
    public void getCountries(final CoronaApiCallback<Countries> userCallback) {
        Call<Countries> call = mCoronaApi.getCountries();
        get(call, userCallback);
    }

    //Cases
    @Override
    public Result<Cases> pullCases() throws IOException {
        Call<Cases> call = mCoronaApi.getCases();
        return pull(call);
    }

    @Override
    public void getCases(final CoronaApiCallback<Cases> userCallback) {
        Call<Cases> call = mCoronaApi.getCases();
        get(call, userCallback);
    }

    // Stats
    @Override
    public Result<WorldStats> pullStats() throws IOException {
        Call<WorldStats> call = mCoronaApi.getStats();
        return pull(call);
    }

    @Override
    public void getStats(final CoronaApiCallback<WorldStats> userCallback) {
        Call<WorldStats> call = mCoronaApi.getStats();
        get(call, userCallback);
    }

    @Override
    public Result<CountryHistory> pullHistoryByCountry(String name) throws IOException {
        Call<CountryHistory> call = mCoronaApi.getHistoryByCountry(name);
        return pull(call);
    }

    @Override
    public void getHistoryByCountry(String name, CoronaApiCallback<CountryHistory> userCallback) {
        Call<CountryHistory> call = mCoronaApi.getHistoryByCountry(name);
        get(call, userCallback);
    }
}
