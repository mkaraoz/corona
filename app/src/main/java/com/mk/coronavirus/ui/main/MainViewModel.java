package com.mk.coronavirus.ui.main;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mk.coronapi.api.Corona;
import com.mk.coronavirus.db.model.CaseByCountry;
import com.mk.coronavirus.db.model.WorldStats;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "_MK " + MainViewModel.class.getName();
    private MainRepository mRepository;
    private LiveData<WorldStats> worldStatsLiveData;
    private LiveData<List<CaseByCountry>> casesLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MainRepository(Corona.publicEndpoint(), application);
    }

    public LiveData<WorldStats> getWorldStats(boolean refreshRequired, NetworkCallbackHome callback) {
        return mRepository.getWorldStats(refreshRequired, callback);
    }

    public LiveData<List<CaseByCountry>> getCases(String order, boolean refreshRequired, NetworkCallbackHome callback) {
        return mRepository.getCases(order, refreshRequired, callback);
    }
}
