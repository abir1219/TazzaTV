package com.textifly.tazzatv.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.textifly.tazzatv.CustomDialog.CustomLanguageDialog;
import com.textifly.tazzatv.Helper.LanguageHelper;
import com.textifly.tazzatv.MainActivity;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnClick();
    }

    @Override
    public void onStart() {
        super.onStart();
        setView();
    }


    private void setView() {
        //Toast.makeText(this, "setView", Toast.LENGTH_SHORT).show();
        resources = LanguageHelper.setLocale(LoginActivity.this, LanguageHelper.getLanguage(LoginActivity.this)).getResources();
        binding.tilEmail.setHint(resources.getString(R.string.email_phone));
        binding.tilPassword.setHint(resources.getString(R.string.password));
        binding.tvLogin.setText(resources.getString(R.string.login));
        binding.tvForgotPassword.setText(resources.getString(R.string.forget_password));
        binding.tvSkip.setText(resources.getString(R.string.skip));
        binding.tvLoginWith.setText(resources.getString(R.string.login_with));
        binding.tvLanguage.setText(resources.getString(R.string.change_language));
        binding.tvLang.setText(": "+resources.getString(R.string.language));

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
                        //Toast.makeText(LoginActivity.this,"You Selected : " + lang, Toast.LENGTH_SHORT).show();
                        setView();
                    }
                });
                break;
            case R.id.llSkip:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
                break;
        }
    }

}