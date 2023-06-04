package com.example.electricitycostcalculator.view;

import static com.example.electricitycostcalculator.helper.Helper.isValidServiceNumber;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricitycostcalculator.dao.AppDatabase;
import com.example.electricitycostcalculator.databinding.ActivityHouseholdActionBinding;
import com.example.electricitycostcalculator.entity.Consumption;
import com.example.electricitycostcalculator.entity.Household;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActionHouseholdActivity extends AppCompatActivity {
    ActivityHouseholdActionBinding binding;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHouseholdActionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initLanguage();
        initButtons();

    }

    private void initLanguage() {
        message = "Boş bırakmayınız ve Hizmet numarası 10 karakter olmalı.";
    }

    private void initButtons() {


        binding.buttonSave.setOnClickListener(view -> {
            if (canBeSend()) {
                saveNewItem();
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        });

    }

    private boolean canBeSend() {
        if (isValidServiceNumber(binding.textviewName.getText().toString()) && !binding.textviewUnit.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }


    private void saveNewItem() {
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        Household household = new Household();
        household.setServiceNumber(binding.textviewName.getText().toString());
        household.setTotal(Integer.parseInt(binding.textviewUnit.getText().toString()));

        List<Household> householdList = appDatabase.dao().getHouseholdList();
        for (int i = 0; i < householdList.size(); i++) {
            if (Objects.equals(householdList.get(i).getServiceNumber(), binding.textviewName.getText().toString())) {
                household = new Household();
                household.setServiceNumber(binding.textviewName.getText().toString()+"/Gerçek Tüketim");
                int newTotal=(Integer.parseInt(binding.textviewUnit.getText().toString())-householdList.get(i).getTotal());
                household.setTotal(newTotal);
                household.setOldReaded(true);
            }
        }

        List<String> stringList = new ArrayList<>();
        double cost = 0;
        int remainingUnits = household.getTotal();

        for (Consumption consumption : appDatabase.dao().getConsumptionList()) {
            int slabUnits = Math.min(consumption.getEndUnits() - consumption.getStartUnits() + 1, remainingUnits);
            double slabCost = slabUnits * consumption.getRate();
            cost += slabCost;
            remainingUnits -= slabUnits;

            System.out.println(slabUnits + " birim x " + consumption.getRate() + " = $" + slabCost);
            stringList.add(slabUnits + " birim x " + consumption.getRate() + " = $" + slabCost);

            if (remainingUnits <= 0) {
                break;
            }
        }
        stringList.add("Toplam Fatura Tutarı = $" + cost);
        System.out.println("Toplam Fatura Tutarı = $" + cost);
        household.setItemList(stringList);
        appDatabase.dao().insertHousehold(household);
        finish();
    }

}