package com.textifly.tazzatv.ui.Home.AllNews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    public static final int item_banner = 1;
    public static final int item_data = 0;

    public AllNewsAdapter(List<Object> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case item_data:
                View dataview = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent, false);
                return new ViewHolder(dataview);
            case item_banner:
            default:
                View bannerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_singlerow, parent, false);
                return new Banneraddviewholder(bannerview);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case item_data:
                ViewHolder hldr = (ViewHolder) holder;
                AllNewsNewsModel model = (AllNewsNewsModel) modelList.get(position);
                hldr.tvTitle.setText(model.getTitle());
                Glide.with(context).load(model.getImage()).into(hldr.ivImage);
                break;
            case item_banner:
            default:
                Banneraddviewholder bvh = (Banneraddviewholder) holder;
                AdView adView = (AdView) modelList.get(position);

                ViewGroup adcardview = (ViewGroup) bvh.itemView;

                if (adcardview.getChildCount() > 0)
                    adcardview.removeAllViews();
                if (adcardview.getParent() != null)
                    ((ViewGroup) adView.getParent()).removeView(adView);

                adcardview.addView(adView);
        }
    }

    /*@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout,parent,false));
        switch (viewType) {
            case item_data:
                View dataView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent, false);
                return new ViewHolder(dataView);
            //break;
            case item_banner:
            default:
                View bannerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_singlerow, parent, false);
                return new Banneraddviewholder(bannerView);
            //break;
        }
    }*/

    /*@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        *//*holder.tvTitle.setText(modelList.get(position).getTitle());
        Glide.with(context).load(modelList.get(position).getImage()).into(holder.ivImage);*//*

        int viewType = getItemViewType(position);
        switch (viewType) {
            case item_data:
                ViewHolder hldr = (ViewHolder) holder;
                AllNewsNewsModel model = (AllNewsNewsModel) modelList.get(position);
                hldr.tvTitle.setText(model.getTitle());
                Glide.with(context).load(model.getImage()).into(hldr.ivImage);
                break;
            case item_banner:
            default:

        }
    }*/

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public int getItemViewType(int position) {
        if (position % AllNewsFragment.ITEM_PER_ADD == 0) {
            return item_banner;
        } else {
            return item_data;
        }
    }

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
        public Banneraddviewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
