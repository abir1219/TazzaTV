package com.textifly.tazzatv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.textifly.tazzatv.MainActivity;
import com.textifly.tazzatv.Model.DrawerModel;
import com.textifly.tazzatv.R;

import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {
    private MainActivity.OnDrawerMenuListener mListener;
    List<DrawerModel> modelList;
    Context context;
    public static int selected_postion = -1;

    public DrawerAdapter(List<DrawerModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrawerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, int position) {
        holder.tvMenu.setText(modelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mListener.onDrawerMenuClick(position);
                } catch (NullPointerException e) {
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class DrawerViewHolder extends RecyclerView.ViewHolder {
        public static TextView tvMenu;

        public DrawerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenu = itemView.findViewById(R.id.tvMenu);
        }
    }

    public void setListenerDrawerMenu(MainActivity.OnDrawerMenuListener listener) {
        mListener = listener;
    }

    public void colorChange(int pos){

    }

}
