package com.example.masuka_app.api;

import com.example.masuka_app.models.country_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface country_api {

    @GET("asia")
    Call<List<country_model>> getcountries();

}
