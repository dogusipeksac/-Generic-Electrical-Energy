package com.example.electricitycostcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.electricitycostcalculator.adapter.ConsumptionAdapter;
import com.example.electricitycostcalculator.adapter.HouseholdAdapter;
import com.example.electricitycostcalculator.dao.AppDatabase;
import com.example.electricitycostcalculator.databinding.ActivityMainBinding;
import com.example.electricitycostcalculator.entity.Consumption;
import com.example.electricitycostcalculator.entity.Household;
import com.example.electricitycostcalculator.view.ActionHouseholdActivity;
import com.example.electricitycostcalculator.view.ActionTableActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private List<Consumption> consumptionList;
    private List<Household> householdList;
    private ConsumptionAdapter consumptionAdapter;
    private HouseholdAdapter householdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadTableList();
        calculateList();
        initButtons();
    }

    private void initButtons() {
        binding.addtableButton.setOnClickListener(view -> startActivityForResult(new Intent(MainActivity.this, ActionTableActivity.class), 100));
        binding.addhouseholdButton.setOnClickListener(view -> {
            startActivityForResult(new Intent(MainActivity.this, ActionHouseholdActivity.class), 200);
        });

    }

    private void loadTableList() {
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        if (appDatabase.dao().getConsumptionList().size() != 0) {
            consumptionList = appDatabase.dao().getConsumptionList();
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.consumptionRecyclerview.setLayoutManager(layoutManager);
            consumptionAdapter = new ConsumptionAdapter(consumptionList, this); // itemList kendi veri listeniz olsun
            binding.consumptionRecyclerview.setAdapter(consumptionAdapter);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            loadTableList();
        }
        if (requestCode == 200) {
            calculateList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void calculateList() {
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        if (appDatabase.dao().getHouseholdList().size() != 0) {
            householdList = appDatabase.dao().getHouseholdList();
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.householdRecyclerview.setLayoutManager(layoutManager);
            householdAdapter = new HouseholdAdapter(householdList, this );
            binding.householdRecyclerview.setAdapter(householdAdapter);
        }

    }


}