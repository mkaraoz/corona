package com.mk.coronavirus.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mk.coronavirus.db.model.WorldStats;

@Dao
public interface WorldStatsDao {
    @Query("SELECT * FROM stats ORDER BY id DESC LIMIT 1 ")
    LiveData<WorldStats> getWorldStats();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStats(WorldStats stats);
}
