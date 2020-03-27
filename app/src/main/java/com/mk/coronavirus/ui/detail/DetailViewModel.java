package com.mk.coronavirus.ui.detail;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mk.coronapi.api.Corona;
import com.mk.coronavirus.db.AppDatabase;
import com.mk.coronavirus.db.model.CountryHistoricalData;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = "_MK " + DetailViewModel.class.getName();
    private DetailRepository detailRepository;
    private LiveData<List<CountryHistoricalData>> countryHistoricalLiveData;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        detailRepository = new DetailRepository(Corona.publicEndpoint(), application, AppDatabase.getInstance(application).countryHistoricalDataDao());
    }

    public LiveData<List<CountryHistoricalData>> getHistoricalData(String name, boolean refreshRequired, NetworkCallbackCountry callback) {
        return detailRepository.getHistoricalDataOf(name, refreshRequired, callback);
    }
}
