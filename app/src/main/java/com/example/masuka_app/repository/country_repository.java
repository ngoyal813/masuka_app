package com.example.masuka_app.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.masuka_app.dao.country_dao;
import com.example.masuka_app.database.country_database;
import com.example.masuka_app.models.country_model;

import java.util.List;

public class country_repository {

    private country_database database;

    public LiveData<List<country_model>> getCountries() {
        return countries;
    }

    private LiveData<List<country_model>> countries;
    private country_dao dao;

    public country_repository(Application application){
        database = country_database.getInstance(application);
        dao = database.country_dao();
        countries = database.country_dao().getallcountries();
    }

    public void insert(List<country_model> country_list){
        new inserttask(database).execute(country_list);
    }

    static class inserttask extends AsyncTask<List<country_model>,Void,Void>{

        private country_dao dao;

        inserttask(country_database database){
            dao  = database.country_dao();
        }
        @Override
        protected Void doInBackground(List<country_model>... lists) {
            dao.insert(lists[0]);
            return null;
        }
    }

    public void deleteall(){
        new deletetask(dao).execute();
    }

    private static class deletetask extends AsyncTask<Void,Void,Void>{
        private country_dao country_dao;
        deletetask(country_dao dao){
            country_dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            country_dao.deleteallcountries();
            return null;
        }
    }
}
