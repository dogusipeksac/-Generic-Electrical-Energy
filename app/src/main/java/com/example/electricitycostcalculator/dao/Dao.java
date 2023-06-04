package com.example.electricitycostcalculator.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.electricitycostcalculator.entity.Consumption;
import com.example.electricitycostcalculator.entity.Household;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insertConsumption(Consumption consumption);

    @Delete
    void deleteConsumption(Consumption consumption);

    @Query("SELECT * FROM Consumption")
    List<Consumption> getConsumptionList();

    @Insert
    void insertHousehold(Household consumption);

    @Delete
    void deleteHousehold(Household consumption);

    @Query("SELECT * FROM Household")
    List<Household> getHouseholdList();

    @Query("SELECT * FROM Household WHERE serviceNumber LIKE :searchQuery")
    LiveData<List<Household>> searchHousehold(String searchQuery);
}
