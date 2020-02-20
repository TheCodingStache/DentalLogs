package com.example.dentallogs.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.tech.setText("username " + techList.getUsername());
        holder.socketIDView.setText("socketID " + techList.getSocketID());
    }


    @Override
    public int getItemCount() {
        return mTechLists.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tech;
        //        TextView location;
        CardView cardView;
        CircleImageView techPhoto;
        TextView socketIDView;

        public MyAdapterViewHolder(final View itemView) {
            super(itemView);
            tech = itemView.findViewById(R.id.tech);
//            location = itemView.findViewById(R.id.location);
            cardView = itemView.findViewById(R.id.cardView);
            techPhoto = itemView.findViewById(R.id.technicianPhoto);
            socketIDView = itemView.findViewById(R.id.socketID);
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SpinnerSelectionActivity.class);
                itemView.getContext().startActivity(intent);
                ((Activity) context).finish();
            });
        }
    }
}
