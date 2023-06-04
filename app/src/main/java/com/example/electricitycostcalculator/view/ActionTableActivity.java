package com.example.electricitycostcalculator.view;

import static com.example.electricitycostcalculator.helper.Helper.isValidServiceNumber;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricitycostcalculator.dao.AppDatabase;
import com.example.electricitycostcalculator.databinding.ActivityActionTableBinding;
import com.example.electricitycostcalculator.entity.Consumption;

public class ActionTableActivity extends AppCompatActivity {
    ActivityActionTableBinding binding;
    String message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActionTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initLanguage();
        binding.buttonSave.setOnClickListener(view -> {
            if (canBeSend()){
                saveNewItem();
            }else{
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void initLanguage() {
        message = "Boş bırakmayınız.";
    }
    private void saveNewItem() {
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        Consumption consumption=new Consumption();
        consumption.setStartUnits(Integer.parseInt(binding.textviewStartUnit.getText().toString()));
        consumption.setEndUnits(Integer.parseInt(binding.textviewEndUnit.getText().toString()));
        consumption.setRate(Integer.parseInt(binding.textviewRate.getText().toString()));
        appDatabase.dao().insertConsumption(consumption);
        finish();
    }

    private boolean canBeSend() {
        if (binding.textviewRate.getText().toString()!="" && !binding.textviewStartUnit.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}