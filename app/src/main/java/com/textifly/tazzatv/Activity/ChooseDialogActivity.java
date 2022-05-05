package com.textifly.tazzatv.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.textifly.tazzatv.Adapter.CityAdapter;
import com.textifly.tazzatv.Application.YoDB;
import com.textifly.tazzatv.Helper.LanguageHelper;
import com.textifly.tazzatv.Model.CityModel;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.Utils.Constants;
import com.textifly.tazzatv.databinding.ActivityChooseDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class ChooseDialogActivity extends AppCompatActivity {
    ActivityChooseDialogBinding binding;
    private Resources resources;
    List<CityModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showChooseLanguageDialog();
    }

    private void showChooseLanguageDialog() {
        resources = LanguageHelper.setLocale(ChooseDialogActivity.this,LanguageHelper.getLanguage(ChooseDialogActivity.this)).getResources();
        AlertDialog.Builder builder = new AlertDialog.Builder(ChooseDialogActivity.this);
        //builder.setTitle(resources.getString(R.string.change_language));

        final View customDialog = getLayoutInflater().inflate(R.layout.language_setting_bottom,null);
        builder.setView(customDialog);
        builder.setCancelable(false);

        AlertDialog dialog
                = builder.create();

        TextView tvBengali = customDialog.findViewById(R.id.tvBengali);
        TextView tvEnglish = customDialog.findViewById(R.id.tvEnglish);
        TextView tvSkip = customDialog.findViewById(R.id.tvSkip);
        ImageView ivBengali = customDialog.findViewById(R.id.ivBengali);
        ImageView ivEnglish = customDialog.findViewById(R.id.ivEnglish);
        LinearLayout llEnglish = customDialog.findViewById(R.id.llEnglish);
        LinearLayout llBengali = customDialog.findViewById(R.id.llBengali);

        if (LanguageHelper.getLanguage(ChooseDialogActivity.this).equalsIgnoreCase("hi")){
            tvBengali.setTextColor(ChooseDialogActivity.this.getResources().getColor(R.color.green4));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvBengali.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.green4));
                tvEnglish.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.white));
            }
            ivBengali.setVisibility(View.VISIBLE);
            ivEnglish.setVisibility(View.INVISIBLE);
        }else {
            tvEnglish.setTextColor(ChooseDialogActivity.this.getResources().getColor(R.color.green4));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvBengali.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.white));
                tvEnglish.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.green4));
            }
            ivBengali.setVisibility(View.INVISIBLE);
            ivEnglish.setVisibility(View.VISIBLE);
        }

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                //startActivity(new Intent(ChooseDialogActivity.this,CityListActivity.class));
                showChooseCityDialog(view);
            }
        });

        llBengali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBengali.setTextColor(ChooseDialogActivity.this.getResources().getColor(R.color.green4));
                tvEnglish.setTextColor(ChooseDialogActivity.this.getResources().getColor(R.color.white));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvBengali.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.green4));
                    tvEnglish.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.white));
                }
                ivBengali.setVisibility(View.VISIBLE);
                ivEnglish.setVisibility(View.INVISIBLE);
                dialog.cancel();
                //Language Change
                LanguageHelper.setLocale(ChooseDialogActivity.this, "hi");
                //startActivity(new Intent(ChooseDialogActivity.this,LoginActivity.class));
                showChooseCityDialog(v);
                /*if (onClickListener != null){
                    onClickListener.onClick("Hindi");
                }*/
            }
        });
        llEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBengali.setTextColor(ChooseDialogActivity.this.getResources().getColor(R.color.white));
                tvEnglish.setTextColor(ChooseDialogActivity.this.getResources().getColor(R.color.green4));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvBengali.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.white));
                    tvEnglish.setCompoundDrawableTintList(ChooseDialogActivity.this.getColorStateList(R.color.green4));
                }
                ivBengali.setVisibility(View.INVISIBLE);
                ivEnglish.setVisibility(View.VISIBLE);
                dialog.cancel();
                //Language Change
                LanguageHelper.setLocale(ChooseDialogActivity.this, "en");
                //startActivity(new Intent(ChooseDialogActivity.this,LoginActivity.class));
                showChooseCityDialog(v);
                /*if (onClickListener != null){
                    onClickListener.onClick("English");
                }*/
            }
        });
        dialog.show();
    }

    private void showChooseCityDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(ChooseDialogActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //final View customDialog = getLayoutInflater().inflate(R.layout.city_list_dialog,null);
        View customDialog = LayoutInflater.from(v.getContext()).inflate(R.layout.city_list_dialog, viewGroup, false);
        builder.setView(customDialog);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        RecyclerView rvCity = customDialog.findViewById(R.id.rvCityList);
        TextView tvSkip = customDialog.findViewById(R.id.tvSkip);

        loadCity(rvCity,dialog);

        dialog.show();


        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                startActivity(new Intent(ChooseDialogActivity.this,CityListActivity.class));
                startActivity(new Intent(ChooseDialogActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
                finish();
            }
        });



    }

    private void loadCity(RecyclerView rvCity, AlertDialog dialog) {
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

        CityAdapter adapter = new CityAdapter(modelList,ChooseDialogActivity.this);
        rvCity.setAdapter(adapter);

        //rvCity.setLayoutManager(new GridLayoutManager(ChooseDialogActivity.this,2));
        rvCity.setLayoutManager(new LinearLayoutManager(ChooseDialogActivity.this,RecyclerView.HORIZONTAL,false));

        adapter.setListner(new onDataRecived() {
            @Override
            public void onCallBack(int pos) {
                String cityName = CityAdapter.cityName;
                YoDB.getPref().write(Constants.CITY_NAME,"",cityName);
                Log.d("CITY_NAME",cityName);
                dialog.dismiss();
                startActivity(new Intent(ChooseDialogActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
                finish();
            }
        });
    }

    public interface onDataRecived {
        void onCallBack(int pos);
    }
}

