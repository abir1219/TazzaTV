package com.textifly.tazzatv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.textifly.tazzatv.Model.CityModel;
import com.textifly.tazzatv.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    List<CityModel> modelList;
    Context context;

    public CityAdapter(List<CityModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.city_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCity.setText(modelList.get(position).getCityName());
        Glide.with(context).load(modelList.get(position).getCityImage()).into(holder.ivCity);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivCity;
        TextView tvCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCity = itemView.findViewById(R.id.ivCity);
            tvCity = itemView.findViewById(R.id.tvCity);
        }
    }
}
