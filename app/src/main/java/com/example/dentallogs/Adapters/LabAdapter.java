package com.example.dentallogs.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Model.Body;
import com.example.dentallogs.R;
import com.example.dentallogs.SpinnerSelectionActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class LabAdapter extends RecyclerView.Adapter<LabAdapter.MyAdapterViewHolder> {
    private List<Body> mTechLists;
    private Context context;

    public LabAdapter(List<Body> mTechLists, Context context) {
        this.mTechLists = mTechLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lab_layout, parent, false);
        return new MyAdapterViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        Body techList = mTechLists.get(position);
        holder.tech.setText(techList.getUsername());
        holder.socketIDView.setText(techList.getSocketID());
        holder.container.setOnClickListener(v1 -> {
            Intent i = new Intent(context, SpinnerSelectionActivity.class);
            Bundle b = new Bundle();
            b.putString("key", techList.getUsername());
            i.putExtra("socketID", techList.getSocketID());
            i.putExtra("username", techList.getUsername());
            Log.d("GIA NA DOUME", "onBindViewHolder: " + techList.getUsername());
            context.startActivity(i);
            ((Activity) context).finish();
        });
    }


    @Override
    public int getItemCount() {
        return mTechLists.size();
    }

    static class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tech;
        CardView cardView;
        CircleImageView techPhoto;
        TextView socketIDView;
        RelativeLayout container;

        public MyAdapterViewHolder(final View itemView) {
            super(itemView);
            tech = itemView.findViewById(R.id.tech);
            cardView = itemView.findViewById(R.id.cardView);
            techPhoto = itemView.findViewById(R.id.technicianPhoto);
            socketIDView = itemView.findViewById(R.id.socketID);
            container = itemView.findViewById(R.id.container);
        }
    }
}
