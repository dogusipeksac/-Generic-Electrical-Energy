package com.example.electricitycostcalculator.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Household")
public class Household {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String serviceNumber;
    private boolean oldReaded;
    private int newTotal;
    private int total;
    private List<String> itemList;

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public int getTotal() {
        return total;
    }

    public boolean isOldReaded() {
        return oldReaded;
    }

    public void setOldReaded(boolean oldReaded) {
        this.oldReaded = oldReaded;
    }

    public int getNewTotal() {
        return newTotal;
    }

    public void setNewTotal(int newTotal) {
        this.newTotal = newTotal;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
