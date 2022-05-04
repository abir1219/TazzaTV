package com.textifly.tazzatv.ui.Home.AllNews.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdView;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.ui.Home.AllNews.AllNewsFragment;
import com.textifly.tazzatv.ui.Home.AllNews.Model.AllNewsNewsModel;

import java.util.List;

public class AllNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //List<AllNewsNewsModel> modelList;
    List<Object> modelList;
    Context context;

    private static final int item_data = 0;
    private static final int item_banner = 1;


    public AllNewsAdapter(List<Object> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case item_banner:
                return new Banneraddviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_singlerow, parent, false));
            case item_data:
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent, false));

        }
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if (position == 0 || modelList.get(position) instanceof AllNewsNewsModel) {
            return item_data;
        } else {
            if (position % AllNewsFragment.ITEM_PER_AD == 0) {
                return item_banner;
            } else {
                return item_data;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        //int viewType = getViewType(position);
        int viewType = getItemViewType(position);
        Log.d("VIEW_TYPE", "VIEW_TYPE: " + viewType);
        switch (viewType) {
            case item_banner:
                if (modelList.get(position) instanceof AdView) {
                    Banneraddviewholder bvh = (Banneraddviewholder) holder;
                    AdView adView = (AdView) modelList.get(position);
                    ViewGroup adcardview = (ViewGroup) bvh.itemView;

                    if (adcardview.getChildCount() > 0) {
                        adcardview.removeAllViews();
                    }

                    if (adcardview.getParent() != null) {
                        ((ViewGroup) adView.getParent()).removeView(adView);
                    }

                    adcardview.addView(adView);
                }
                break;
            case item_data:
            default:
                if (modelList.get(position) instanceof AllNewsNewsModel) {
                    ViewHolder hldr = (ViewHolder) holder;
                    AllNewsNewsModel model = (AllNewsNewsModel) modelList.get(position);
                    hldr.tvTitle.setText(model.getTitle());
                    Glide.with(context).load(model.getImage()).into(hldr.ivImage);
                }
                break;
        }
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*public int getViewType(int position) {
        Log.d("POSITION","Position: "+position);
        if (position % AllNewsFragment.ITEM_PER_AD == 0) {
            return item_banner;
        } else {
            return item_data;
        }
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivImage = itemView.findViewById(R.id.ivImg);
        }
    }

    public static class Banneraddviewholder extends RecyclerView.ViewHolder {
        CardView ad_card_view;

        public Banneraddviewholder(@NonNull View itemView) {
            super(itemView);
            ad_card_view = itemView.findViewById(R.id.ad_card_view);
        }
    }
}
