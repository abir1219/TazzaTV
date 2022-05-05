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
import com.textifly.tazzatv.Activity.ChooseDialogActivity;
import com.textifly.tazzatv.CustomDialog.CustomLanguageDialog;
import com.textifly.tazzatv.Model.CityModel;
import com.textifly.tazzatv.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    List<CityModel> modelList;
    Context context;
    public static String cityName = "";
    ChooseDialogActivity.onDataRecived onDataRecived;


    public CityAdapter(List<CityModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.city_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCity.setText(modelList.get(position).getCityName());
        Glide.with(context).load(modelList.get(position).getCityImage()).into(holder.ivCity);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityName = modelList.get(position).getCityName();
                onDataRecived.onCallBack(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCity;
        TextView tvCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCity = itemView.findViewById(R.id.ivCity);
            tvCity = itemView.findViewById(R.id.tvCity);
        }
    }

    public void setListner(ChooseDialogActivity.onDataRecived onDataRecived4) {
        this.onDataRecived = onDataRecived4;
    }
}
