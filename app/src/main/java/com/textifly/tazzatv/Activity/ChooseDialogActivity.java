package com.textifly.tazzatv.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.textifly.tazzatv.Helper.LanguageHelper;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.databinding.ActivityChooseDialogBinding;

public class ChooseDialogActivity extends AppCompatActivity {
    ActivityChooseDialogBinding binding;
    private Resources resources;

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
                startActivity(new Intent(ChooseDialogActivity.this,CityListActivity.class));
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
                startActivity(new Intent(ChooseDialogActivity.this,LoginActivity.class));
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
                startActivity(new Intent(ChooseDialogActivity.this,LoginActivity.class));
                /*if (onClickListener != null){
                    onClickListener.onClick("English");
                }*/
            }
        });
        dialog.show();
    }
}