package com.example.masuka_app.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.masuka_app.converters.array_list_converter;
import com.example.masuka_app.converters.language_list_converter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "countries")
public class country_model {

    public country_model(String name, String capital, String flag, String region, String subregion, String population, ArrayList borders, List<language_model> languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public ArrayList getBorders() {
        return borders;
    }

    public void setBorders(ArrayList borders) {
        this.borders = borders;
    }

    public List<language_model> getLanguages() {
        return languages;
    }

//    public void setLanguages(List<language_model> languages) {
//        this.languages = languages;
//    }

    @Override
    public String toString() {
        return "country_model{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", flag='" + flag + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population='" + population + '\'' +
                ", borders=" + borders +
                ", languages=" + languages +
                '}';
    }

    //name, capital, flag(display image in app), region,
    //subregion, population, borders & languages.


    @NonNull
    @PrimaryKey
    @Expose
    private String name;
    @Expose
    private String capital;
    @Expose
    private String flag;
    @Expose
    private String region;
    @Expose
    private String subregion;
    @Expose
    private String population;
    @Expose
    @TypeConverters({array_list_converter.class})
    private ArrayList borders;
    @Expose
    @TypeConverters({language_list_converter.class})
    private List<language_model> languages;

}
