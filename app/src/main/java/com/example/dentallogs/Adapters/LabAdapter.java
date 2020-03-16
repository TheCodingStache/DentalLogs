package com.example.dentallogs.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Model.Body;
import com.example.dentallogs.R;
import com.example.dentallogs.SpinnerSelectionActivity;

import java.util.List;
import java.util.prefs.Preferences;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class LabAdapter extends RecyclerView.Adapter<LabAdapter.MyAdapterViewHolder>  {
    private List<Body> mTechLists;
    private Context context;
    private String SHARED_PREFS = "sharedPrefs";


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
//        holder._id.setText(techList.getId());
        holder.container.setOnClickListener(v1 -> {
            Intent i = new Intent(context, SpinnerSelectionActivity.class);
            i.putExtra("socketID", techList.getSocketID());
            i.putExtra("username", techList.getUsername());
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
        TextView _id;
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
