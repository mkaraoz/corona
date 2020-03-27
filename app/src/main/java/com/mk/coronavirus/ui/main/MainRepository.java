package com.mk.coronavirus.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.mk.coronapi.api.CoronaApi;
import com.mk.coronapi.api.Result;
import com.mk.coronapi.pojo.Cases;
import com.mk.coronapi.pojo.CountriesStat;
import com.mk.coronavirus.db.AppDatabase;
import com.mk.coronavirus.db.dao.CaseByCountryDao;
import com.mk.coronavirus.db.dao.WorldStatsDao;
import com.mk.coronavirus.db.model.CaseByCountry;
import com.mk.coronavirus.db.model.WorldStats;
import com.mk.coronavirus.util.NumberParser;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository {
    private static final String TAG = "_MK " + MainRepository.class.getName();

    private CoronaApi corona;
    private WorldStatsDao worldStatsDao;
    private CaseByCountryDao caseByCountryDao;

    public MainRepository(CoronaApi corona, Context context) {
        this.corona = corona;
        this.worldStatsDao = AppDatabase.getInstance(context).worldStatsDao();
        this.caseByCountryDao = AppDatabase.getInstance(context).caseByCountryDao();
    }

    public LiveData<WorldStats> getWorldStats(boolean refreshRequired, NetworkCallbackHome callback) {
        if (refreshRequired) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Result<com.mk.coronapi.pojo.WorldStats> result = corona.pullStats();
                        if (result.responseCode == 200) {
                            WorldStats stats = new WorldStats(result.body.getTotalCases(), result.body.getTotalDeaths(), result.body.getTotalRecovered(), result.body.getStatisticTakenAt());
                            worldStatsDao.insertStats(stats);
                            callback.onComplete();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(), e);
                        callback.onComplete();
                    }
                }
            });
            executor.shutdown();
        }

        return worldStatsDao.getWorldStats();
    }

    public LiveData<List<CaseByCountry>> getCases(final String order, boolean refreshRequired, NetworkCallbackHome callback) {
        if (refreshRequired) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Result<Cases> result = corona.pullCases();
                        if (result.responseCode == 200) {
                            Cases cases = result.body;
                            List<CountriesStat> stats = cases.getCountriesStat();
                            for (CountriesStat cs : stats) {
                                String countryName = cs.getCountryName();
                                if (countryName.equals("North Macedonia"))
                                    countryName = "Macedonia";
                                if (countryName.equals("Diamond Princess"))
                                    continue; // is this even a real country?
                                int caseCount = NumberParser.toInt(cs.getCases());
                                int deathCount = NumberParser.toInt(cs.getDeaths());
                                int recoverCount = NumberParser.toInt(cs.getTotalRecovered());
                                int activeCount = NumberParser.toInt(cs.getActiveCases());
                                int casePer1Million = NumberParser.toInt(cs.getTotalCasesPer1mPopulation());
                                CaseByCountry cbs = new CaseByCountry(countryName, caseCount, deathCount, recoverCount, activeCount, casePer1Million);
                                caseByCountryDao.insertCaseByCountry(cbs);
                            }
                            callback.onComplete();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(), e);
                        callback.onComplete();
                    }
                }
            });
            executor.shutdown();
        }

        if (order.equals(CaseByCountry.SORT_BY_CASE_COUNT))
            return caseByCountryDao.getCasesByCountryOrderByTocalCases();
        else if (order.equals(CaseByCountry.SORT_BY_DEATH_COUNT))
            return caseByCountryDao.getCasesByCountryOrderByDeath();
        else
            return caseByCountryDao.getCasesByCountryOrderByName();
    }
}
