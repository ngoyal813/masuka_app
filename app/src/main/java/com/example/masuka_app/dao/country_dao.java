package com.example.masuka_app.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.masuka_app.models.country_model;

import java.util.List;

@Dao
public interface country_dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<country_model> country_models);

    @Query("SELECT * FROM countries")
    LiveData<List<country_model>> getallcountries();

    @Query("DELETE FROM countries")
    void deleteallcountries();


}
