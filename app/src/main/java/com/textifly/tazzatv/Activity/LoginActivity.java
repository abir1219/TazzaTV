package com.textifly.tazzatv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.textifly.tazzatv.CustomDialog.CustomLanguageDialog;
import com.textifly.tazzatv.MainActivity;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnClick();
    }

    private void btnClick() {
        binding.llChangeLanguage.setOnClickListener(this);
        binding.llSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llChangeLanguage:
                //showChangeLanguageBottomSheetDialog();
                new CustomLanguageDialog(LoginActivity.this, view, new CustomLanguageDialog.OnClickListener() {
                    @Override
                    public void onClick(String lang) {
                        Toast.makeText(LoginActivity.this,"You Selected : " + lang, Toast.LENGTH_SHORT).show();
                        //setViews();
                    }
                });
                break;
            case R.id.llSkip:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
                break;
        }
    }

    private void showChangeLanguageBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.language_setting_bottom);
        bottomSheetDialog.setCancelable(true);

        bottomSheetDialog.show();
    }
}