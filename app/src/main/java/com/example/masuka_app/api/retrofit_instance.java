package com.example.masuka_app.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit_instance {

    public static String BASE_URL = "https://restcountries.eu/rest/v2/region/";
    private static Retrofit retrofit;

    public static Retrofit getRetroClient()
    {
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }
}
