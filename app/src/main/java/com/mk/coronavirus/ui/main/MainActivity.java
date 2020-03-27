package com.mk.coronavirus.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mk.coronavirus.R;
import com.mk.coronavirus.db.model.CaseByCountry;
import com.mk.coronavirus.db.model.WorldStats;
import com.mk.coronavirus.ui.detail.DetailActivity;
import com.mk.coronavirus.ui.menu.SettingsActivity;
import com.mk.coronavirus.util.FlagMatcher;
import com.mk.coronavirus.util.Network;
import com.mk.coronavirus.util.TimeConverter;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static com.mk.coronavirus.db.model.CaseByCountry.SORT_BY_DEATH_COUNT;

public class MainActivity extends AppCompatActivity implements NetworkCallbackHome, DatarowAdapter.OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private SwipeRefreshLayout swipeContainer;
    private DatarowAdapter datarowAdapter;
    private TextView tvDeathCount, tvCaseCount, tvRecoverCount, tvUpdate;
    private SharedPreferences preferences;
    private MainViewModel viewModel;

    private String order = CaseByCountry.getOrder(SORT_BY_DEATH_COUNT);
    private boolean orderChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.ab);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        tvCaseCount = findViewById(R.id.tvCaseCount);
        tvDeathCount = findViewById(R.id.tvDeathCount);
        tvRecoverCount = findViewById(R.id.tvRecoverCount);
        tvUpdate = findViewById(R.id.tvUpdate);
        swipeContainer = findViewById(R.id.swipeContainer);
        int c1 = getResources().getColor(R.color.colorAccent);
        swipeContainer.setColorSchemeColors(c1, Color.BLUE, c1);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                observeWorldStats(true, MainActivity.this);
                observeCases(order, true, MainActivity.this);
            }
        });
        datarowAdapter = new DatarowAdapter(MainActivity.this, new ArrayList<>());
        RecyclerView recyclerView = findViewById(R.id.rvList);
        // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(datarowAdapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);

        String orderPref = preferences.getString(getString(R.string.key_country_order), "0");
        order = CaseByCountry.getOrder(orderPref);

        initApp();
    }

    private void initApp() {
        if (!Network.isOnline(this)) {
            Toast.makeText(this, getString(R.string.no_network), Toast.LENGTH_SHORT).show();
        }

        //TODO: schedulaAutoUpdates();

        boolean isRefreshRequired = TimeConverter.has5minPassed(preferences.getLong(LAST_UPDATE_HOME, 0L));
        observeWorldStats(isRefreshRequired, this);
        observeCases(order, isRefreshRequired, this);
    }

    private void observeCases(String order, boolean refreshRequired, NetworkCallbackHome callback) {
        viewModel.getCases(order, refreshRequired, callback).observe(this, caseObserver);
    }

    private Observer<List<CaseByCountry>> caseObserver = new Observer<List<CaseByCountry>>() {
        @Override
        public void onChanged(@Nullable List<CaseByCountry> cases) {
            if (cases == null || cases.isEmpty()) return;
            Deque<Datarow> rows = new LinkedList<>();
            for (CaseByCountry cbc : cases) {
                Datarow dr = new Datarow();
                dr.setFlag(FlagMatcher.getFlag(cbc.getName()));
                dr.setDeathCount(cbc.getDeaths());
                dr.setCaseCount(cbc.getCaseCount());
                dr.setName(cbc.getName());
                if (cbc.getName().equals("Turkey")) rows.addFirst(dr);

                else rows.add(dr);
            }

            //if (rows.get(0) == null) rows.remove(0);
            datarowAdapter.clear();
            datarowAdapter.update(new ArrayList<>(rows));
        }
    };

    private void observeWorldStats(boolean refreshRequired, NetworkCallbackHome callback) {
        viewModel.getWorldStats(refreshRequired, callback).observe(this, worldStatsObserver);
    }

    private Observer<WorldStats> worldStatsObserver = new Observer<WorldStats>() {
        @Override
        public void onChanged(@Nullable WorldStats ws) {
            if (ws == null) return;
            tvUpdate.setText("Last update: " + TimeConverter.zuluToGmtPlus3(ws.getDateTime()));
            tvCaseCount.setText(String.valueOf(ws.getTotalCases()));
            tvDeathCount.setText(String.valueOf(ws.getTotalDeaths()));
            tvRecoverCount.setText(String.valueOf(ws.getTotalRecovered()));
        }
    };

    @Override
    public void onComplete() {
        swipeContainer.setRefreshing(false);
        preferences.edit().putLong(LAST_UPDATE_HOME, System.currentTimeMillis()).apply();
    }

    @Override
    public void onItemClick(String country, int flag, ImageView ivFlag) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("name", country);
        i.putExtra("flag", flag);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, ivFlag, "FLAG");
        startActivity(i, options.toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSettings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isRefreshRequired = TimeConverter.has5minPassed(preferences.getLong(LAST_UPDATE_HOME, 0L));
        if (orderChanged || isRefreshRequired) {
            observeCases(order, isRefreshRequired, this);
            orderChanged = false;
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.key_country_order))) {
            String orderPref = preferences.getString(getString(R.string.key_country_order), "0");
            order = CaseByCountry.getOrder(orderPref);
            orderChanged = true;
        }
    }

    private String LAST_UPDATE_HOME = "LAST_UPDATE_HOME";
}
