package com.example.masuka_app.converters;

import androidx.room.TypeConverter;

import com.example.masuka_app.models.language_model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class language_list_converter {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<language_model> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<language_model>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<language_model> someObjects) {
        return gson.toJson(someObjects);
    }
}
