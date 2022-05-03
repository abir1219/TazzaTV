package com.textifly.tazzatv.CustomDialog;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.textifly.tazzatv.Helper.LanguageHelper;
import com.textifly.tazzatv.R;

public class CustomLanguageDialog {
    public interface OnClickListener {
        void onClick(String lang);
    }

    private OnClickListener onClickListener;

    public CustomLanguageDialog(Context mContext, View view, OnClickListener onClickListener) {
        initViews(mContext, view, onClickListener);
    }

    private void initViews(Context mContext, View view, OnClickListener onClickListener) {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
        View sheetView = LayoutInflater.from(mContext).inflate(R.layout.language_setting_bottom, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();

        TextView tvBengali = mBottomSheetDialog.findViewById(R.id.tvBengali);
        TextView tvEnglish = mBottomSheetDialog.findViewById(R.id.tvEnglish);
        ImageView ivBengali = mBottomSheetDialog.findViewById(R.id.ivBengali);
        ImageView ivEnglish = mBottomSheetDialog.findViewById(R.id.ivEnglish);
        LinearLayout llEnglish = mBottomSheetDialog.findViewById(R.id.llEnglish);
        LinearLayout llBengali = mBottomSheetDialog.findViewById(R.id.llBengali);

        if (LanguageHelper.getLanguage(mContext).equalsIgnoreCase("bn")){
            tvBengali.setTextColor(mContext.getResources().getColor(R.color.green4));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvBengali.setCompoundDrawableTintList(mContext.getColorStateList(R.color.green4));
                tvEnglish.setCompoundDrawableTintList(mContext.getColorStateList(R.color.white));
            }
            ivBengali.setVisibility(View.VISIBLE);
            ivEnglish.setVisibility(View.INVISIBLE);
        }else {
            tvEnglish.setTextColor(mContext.getResources().getColor(R.color.green4));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvBengali.setCompoundDrawableTintList(mContext.getColorStateList(R.color.white));
                tvEnglish.setCompoundDrawableTintList(mContext.getColorStateList(R.color.green4));
            }
            ivBengali.setVisibility(View.INVISIBLE);
            ivEnglish.setVisibility(View.VISIBLE);
        }

        llBengali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBengali.setTextColor(mContext.getResources().getColor(R.color.green4));
                tvEnglish.setTextColor(mContext.getResources().getColor(R.color.white));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvBengali.setCompoundDrawableTintList(mContext.getColorStateList(R.color.green4));
                    tvEnglish.setCompoundDrawableTintList(mContext.getColorStateList(R.color.white));
                }
                ivBengali.setVisibility(View.VISIBLE);
                ivEnglish.setVisibility(View.INVISIBLE);
                mBottomSheetDialog.cancel();
                //Language Change
                LanguageHelper.setLocale(mContext, "bn");
                if (onClickListener != null){
                    onClickListener.onClick("Bengali");
                }
            }
        });
        llEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBengali.setTextColor(mContext.getResources().getColor(R.color.white));
                tvEnglish.setTextColor(mContext.getResources().getColor(R.color.green4));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvBengali.setCompoundDrawableTintList(mContext.getColorStateList(R.color.white));
                    tvEnglish.setCompoundDrawableTintList(mContext.getColorStateList(R.color.green4));
                }
                ivBengali.setVisibility(View.INVISIBLE);
                ivEnglish.setVisibility(View.VISIBLE);
                mBottomSheetDialog.cancel();
                //Language Change
                LanguageHelper.setLocale(mContext, "en");
                if (onClickListener != null){
                    onClickListener.onClick("English");
                }
            }
        });

    }


}
