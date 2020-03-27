package com.mk.coronavirus.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mk.coronavirus.db.model.CaseByCountry;

import java.util.List;

@Dao
public interface CaseByCountryDao {
    @Query("SELECT * FROM cases where caseCount > 100 ORDER BY deaths DESC")
    LiveData<List<CaseByCountry>> getCasesByCountryOrderByDeath();

    @Query("SELECT * FROM cases where caseCount > 100 ORDER BY caseCount DESC")
    LiveData<List<CaseByCountry>> getCasesByCountryOrderByTocalCases();

    @Query("SELECT * FROM cases where caseCount > 100 ORDER BY name ASC")
    LiveData<List<CaseByCountry>> getCasesByCountryOrderByName();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCaseByCountry(CaseByCountry caseByCountry);
}
