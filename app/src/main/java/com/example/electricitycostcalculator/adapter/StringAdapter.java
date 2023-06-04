package com.example.electricitycostcalculator.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.electricitycostcalculator.databinding.ItemStringBinding;

import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {

    private List<String> stringList;
    private Context context;

    public StringAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public StringAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemStringBinding binding = ItemStringBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StringAdapter.ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(StringAdapter.ViewHolder holder, int position) {
        holder.binding.textview.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemStringBinding binding;

        public ViewHolder(ItemStringBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
