package com.example.electricitycostcalculator.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.electricitycostcalculator.dao.AppDatabase;
import com.example.electricitycostcalculator.databinding.ItemConsumptionBinding;
import com.example.electricitycostcalculator.entity.Consumption;

import java.util.List;

public class ConsumptionAdapter extends RecyclerView.Adapter<ConsumptionAdapter.ViewHolder> {

    private List<Consumption> consumptionList;
    private Context context;

    public ConsumptionAdapter(List<Consumption> consumptionList, Context context) {
        this.consumptionList = consumptionList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemConsumptionBinding binding = ItemConsumptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Consumption consumption = consumptionList.get(position);
        holder.binding.totalUnitText.setText(consumption.getStartUnits() + "-" + consumption.getEndUnits() + "units");
        holder.binding.totalRateText.setText("$" + consumption.getRate() + "/ unit");
        holder.binding.delete.setOnClickListener(view -> {
            new AlertDialog.Builder(context).setTitle("Silme Onayı").setMessage("İşlemi silmek istediğinize Emin Misiniz?").setPositiveButton("Evet", (dialog, which) -> {
                deleteItem(consumption);
                notifyItemRemoved(position);
                consumptionList.remove(consumption);
            }).setNegativeButton("Hayır", (dialog, which) -> {
                Toast.makeText(context, "Vazgeçildi.", Toast.LENGTH_SHORT).show();
            }).create().show();


        });

    }
    private void deleteItem(Consumption consumption) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        appDatabase.dao().deleteConsumption(consumption);
    }
    @Override
    public int getItemCount() {
        return consumptionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemConsumptionBinding binding;

        public ViewHolder(ItemConsumptionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
