package com.example.dentallogs.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Model.TechList;
import com.example.dentallogs.R;
import com.example.dentallogs.SpinnerSelectionActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class LabAdapter extends RecyclerView.Adapter<LabAdapter.MyAdapterViewHolder> {
    private List<TechList> mTechLists;
    private Context context;

    public LabAdapter(List<TechList> mTechLists, Context context) {
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
        TechList techList = mTechLists.get(position);
        holder.tech.setText("Όνομα: " + techList.getTech());
        holder.location.setText("Τοποθεσία: " + techList.getLocation());
        holder.techPhoto.setImageDrawable(context.getResources().getDrawable(techList.getPhoto()));
    }


    @Override
    public int getItemCount() {
        return mTechLists.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tech;
        TextView location;
        CardView cardView;
        CircleImageView techPhoto;

        public MyAdapterViewHolder(final View itemView) {
            super(itemView);
            tech = itemView.findViewById(R.id.tech);
            location = itemView.findViewById(R.id.location);
            cardView = itemView.findViewById(R.id.cardView);
            techPhoto = itemView.findViewById(R.id.technicianPhoto);
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SpinnerSelectionActivity.class);
                itemView.getContext().startActivity(intent);
                ((Activity)context).finish();
            });
        }
    }
}
