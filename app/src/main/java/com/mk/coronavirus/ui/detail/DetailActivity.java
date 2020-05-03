package com.mk.coronavirus.ui.detail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.mk.coronavirus.R;
import com.mk.coronavirus.db.model.CountryHistoricalData;
import com.mk.coronavirus.util.Network;
import com.mk.coronavirus.util.TimeConverter;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements NetworkCallbackCountry {

    private DetailViewModel viewModel;
    private TextView tvDeathCount, tvCaseCount, tvRecoverCount;
    private HistoryRawAdapter historyRawAdapter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle b = getIntent().getExtras();
        if (b == null) {
            finish();
            return;
        }

        String name = b.getString("name");
        int flag = b.getInt("flag");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.ab_detail);
        ImageView image = getSupportActionBar().getCustomView().findViewById(R.id.lefty);
        TextView text = getSupportActionBar().getCustomView().findViewById(R.id.tvTitle);
        text.setText(name);
        image.setImageResource(flag);

        setTitle(name);

        tvCaseCount = findViewById(R.id.tvCaseCount);
        tvDeathCount = findViewById(R.id.tvDeathCount);
        tvRecoverCount = findViewById(R.id.tvRecoverCount);

        historyRawAdapter = new HistoryRawAdapter(DetailActivity.this, new ArrayList<>());
        RecyclerView recyclerView = findViewById(R.id.rvHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        recyclerView.setAdapter(historyRawAdapter);
        recyclerView.getItemAnimator().setChangeDuration(0);

        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        init(name);
    }

    private void init(String name) {
        if (!Network.isOnline(this)) {
            Toast.makeText(this, getString(R.string.no_network), Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isRefreshRequired = TimeConverter.has5minPassed(preferences.getLong(LAST_UPDATE_DETAIL + name, 0L));
        observeHistoricalData(name, isRefreshRequired, this);
    }

    private void observeHistoricalData(String name, boolean refreshRequired, NetworkCallbackCountry callback) {
        viewModel.getHistoricalData(name, refreshRequired, callback).observe(this, historyObserver);
    }

    private Observer<List<CountryHistoricalData>> historyObserver = new Observer<List<CountryHistoricalData>>() {
        @Override
        public void onChanged(@Nullable List<CountryHistoricalData> historicalDataList) {
            if (historicalDataList == null || historicalDataList.isEmpty()) return;

            CountryHistoricalData data = historicalDataList.get(0);

            tvCaseCount.setText(String.valueOf(data.getTotalCases()));
            tvDeathCount.setText(String.valueOf(data.getTotalDeaths()));
            tvRecoverCount.setText(String.valueOf(data.getTotalRecovered()));

            historyRawAdapter.clear();
            historyRawAdapter.update(historicalDataList);
        }
    };

    @Override
    public void onComplete(String name) {
        preferences.edit().putLong(LAST_UPDATE_DETAIL + name, System.currentTimeMillis()).apply();
    }

    @Override
    public void onFail() {
        // do not update refresh time
    }

    private String LAST_UPDATE_DETAIL = "LAST_UPDATE_DETAIL";
}
