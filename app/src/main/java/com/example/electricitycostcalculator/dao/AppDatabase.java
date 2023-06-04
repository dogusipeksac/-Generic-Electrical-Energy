package com.example.electricitycostcalculator.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.electricitycostcalculator.helper.ListToStringConverter;
import com.example.electricitycostcalculator.entity.Consumption;
import com.example.electricitycostcalculator.entity.Household;

@Database(entities = {Consumption.class, Household.class}, version = 1)
@TypeConverters({ListToStringConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao dao();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
