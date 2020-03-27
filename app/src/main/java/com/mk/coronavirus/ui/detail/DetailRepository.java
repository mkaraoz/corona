package com.mk.coronavirus.ui.detail;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.mk.coronapi.api.CoronaApi;
import com.mk.coronapi.api.Result;
import com.mk.coronapi.pojo.CountryHistory;
import com.mk.coronapi.pojo.StatByCountry;
import com.mk.coronavirus.db.dao.CountryHistoricalDataDao;
import com.mk.coronavirus.db.model.CountryHistoricalData;
import com.mk.coronavirus.util.NumberParser;
import com.mk.coronavirus.util.TimeConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DetailRepository {
    private static final String TAG = "_MK " + DetailRepository.class.getName();

    private CoronaApi corona;
    private CountryHistoricalDataDao countryHistoricalDataDao;

    public DetailRepository(CoronaApi corona, Context context, CountryHistoricalDataDao countryHistoricalDataDao) {
        this.corona = corona;
        this.countryHistoricalDataDao = countryHistoricalDataDao;
    }

    public LiveData<List<CountryHistoricalData>> getHistoricalDataOf(String country, boolean refreshRequired, NetworkCallbackCountry callback) {
        if (refreshRequired) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Result<CountryHistory> result = corona.pullHistoryByCountry(country);
                        if (result.responseCode == 200) {
                            CountryHistory countryHistory = result.body;
                            List<StatByCountry> statByCountryList = countryHistory.getStatByCountry();
                            List<CountryHistoricalData> historicalDataList = new ArrayList<>();

                            //reverse the list
                            Collections.reverse(statByCountryList);
                            String currentDate = "";
                            for (StatByCountry stat : statByCountryList) {
                                String date = stat.getRecordDate().substring(0, 10);
                                if (!date.equals(currentDate)) {
                                    CountryHistoricalData chd = new CountryHistoricalData(stat.getCountryName(),
                                            NumberParser.toInt(stat.getTotalCases()),
                                            NumberParser.toInt(stat.getNewCases()),
                                            NumberParser.toInt(stat.getTotalDeaths()),
                                            NumberParser.toInt(stat.getNewDeaths()),
                                            NumberParser.toInt(stat.getTotalRecovered()),
                                            date,
                                            TimeConverter.currentTimeInMillis());
                                    currentDate = date;
                                    historicalDataList.add(chd);
                                }
                            }

                            for (CountryHistoricalData chd : historicalDataList) {
                                countryHistoricalDataDao.insert(chd);
                            }
                            callback.onComplete(country);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(), e);
                        callback.onFail();
                    }
                }
            });
            executor.shutdown();
        }
        return countryHistoricalDataDao.getHistoricalData(country);
    }
}
