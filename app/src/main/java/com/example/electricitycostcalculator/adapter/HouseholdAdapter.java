package com.example.electricitycostcalculator.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electricitycostcalculator.dao.AppDatabase;
import com.example.electricitycostcalculator.databinding.ItemHouseholdBinding;
import com.example.electricitycostcalculator.entity.Household;

import java.util.List;

public class HouseholdAdapter extends RecyclerView.Adapter<HouseholdAdapter.ViewHolder> {

    private List<Household> householdList;
    private Context context;
    private StringAdapter stringAdapter;

    public HouseholdAdapter(List<Household> householdList, Context context) {
        this.householdList = householdList;
        this.context = context;
    }

    @Override
    public HouseholdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHouseholdBinding binding = ItemHouseholdBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HouseholdAdapter.ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HouseholdAdapter.ViewHolder holder, int position) {
        Household household = householdList.get(position);
        holder.binding.textviewHouseholdName.setText(household.getServiceNumber());
        holder.binding.textviewFirstRead.setText("Daha önce okundu mu?->" + household.isOldReaded());
        holder.binding.textviewTotal.setText("Total:$" + household.getTotal());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.binding.recyclerviewString.setLayoutManager(layoutManager);
        stringAdapter = new StringAdapter(household.getItemList(), context);
        holder.binding.recyclerviewString.setAdapter(stringAdapter);
        holder.binding.delete.setOnClickListener(view -> {
            new AlertDialog.Builder(context).setTitle("Silme Onayı").setMessage("İşlemi silmek istediğinize Emin Misiniz?").setPositiveButton("Evet", (dialog, which) -> {
                deleteItem(household);
                notifyItemRemoved(position);
                householdList.remove(household);
            }).setNegativeButton("Hayır", (dialog, which) -> {
                Toast.makeText(context, "Vazgeçildi.", Toast.LENGTH_SHORT).show();
            }).create().show();

        });
    }

    private void deleteItem(Household household) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        appDatabase.dao().deleteHousehold(household);
    }

    @Override
    public int getItemCount() {
        return householdList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemHouseholdBinding binding;

        public ViewHolder(ItemHouseholdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
