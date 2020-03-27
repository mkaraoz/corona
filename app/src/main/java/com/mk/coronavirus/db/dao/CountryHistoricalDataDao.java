package com.mk.coronavirus.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mk.coronavirus.db.model.CountryHistoricalData;

import java.util.List;

@Dao
public interface CountryHistoricalDataDao {
    @Query("SELECT * FROM history where name = :name ORDER BY id")
    LiveData<List<CountryHistoricalData>> getHistoricalData(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CountryHistoricalData countryHistoricalData);
}
