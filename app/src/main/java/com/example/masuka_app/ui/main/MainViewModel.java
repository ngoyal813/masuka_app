package com.example.masuka_app.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.masuka_app.models.country_model;
import com.example.masuka_app.repository.country_repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel{

    private country_repository repository;
    private LiveData<List<country_model>> country_list;

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
        repository = new country_repository(application);
        country_list = repository.getCountries();
    }

    public LiveData<List<country_model>> getCountry_list() {
        return country_list;
    }

    public void insertcountry(List<country_model> list){
        repository.insert(list);
    }

    public void deletealldata(){
        repository.deleteall();
    }

}