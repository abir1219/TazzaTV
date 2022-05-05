package com.textifly.tazzatv.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.textifly.tazzatv.Adapter.CityAdapter;
import com.textifly.tazzatv.Model.CityModel;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.databinding.ActivityCityListBinding;

import java.util.ArrayList;
import java.util.List;

public class CityListActivity extends AppCompatActivity {
    ActivityCityListBinding binding;
    List<CityModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRvLayout();
        loadCityList();
    }

    private void setRvLayout() {
        binding.rvCityList.setLayoutManager(new GridLayoutManager(CityListActivity.this,2));
    }

    private void loadCityList() {
        int[] cityImage = new int[]{
                R.drawable.kolkata, R.drawable.mumbai, R.drawable.delhi, R.drawable.chennai
        };

        String[] cityName = new String[]{
                "Kolkata", "Mumbai", "Delhi", "Chennai"
        };

        modelList = new ArrayList<>();
        for (int i = 0; i < cityImage.length; i++) {
            modelList.add(new CityModel(cityName[i], cityImage[i]));
        }

        CityAdapter adapter = new CityAdapter(modelList,CityListActivity.this);
        binding.rvCityList.setAdapter(adapter);
    }
}