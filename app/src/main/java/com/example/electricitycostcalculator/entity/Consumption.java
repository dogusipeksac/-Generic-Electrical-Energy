package com.example.electricitycostcalculator.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Consumption")
public class Consumption {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int startUnits;
    private int endUnits;
    private double rate;


    public Consumption() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartUnits() {
        return startUnits;
    }

    public void setStartUnits(int startUnits) {
        this.startUnits = startUnits;
    }

    public int getEndUnits() {
        return endUnits;
    }

    public void setEndUnits(int endUnits) {
        this.endUnits = endUnits;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
