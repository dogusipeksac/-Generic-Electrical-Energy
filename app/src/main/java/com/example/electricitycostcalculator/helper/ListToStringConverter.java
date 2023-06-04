package com.example.electricitycostcalculator.helper;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListToStringConverter {
    @TypeConverter
    public static String fromList(List<String> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.toJson(list, type);
    }

    @TypeConverter
    public static List<String> toList(String string) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(string, type);
    }
}

