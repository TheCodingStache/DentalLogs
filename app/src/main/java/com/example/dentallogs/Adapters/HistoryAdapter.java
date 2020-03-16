package com.example.dentallogs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Model.ModelHistory;
import com.example.dentallogs.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyAdapterViewHolder> {
    private List<ModelHistory> mTechLists;
    private Context context;


    public HistoryAdapter(List<ModelHistory> mTechLists, Context context) {
        this.mTechLists = mTechLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new MyAdapterViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        ModelHistory modelHistory = mTechLists.get(position);
        holder.name.setText("Όνομα: " + modelHistory.getName());
        holder.lastName.setText("Επώνυμο: " + modelHistory.getLastName());
        holder.date.setText("Ημερομηνία Παραλαβής: " + modelHistory.getDate());
        holder.gender.setText("Φύλλο: " + modelHistory.getGender());
        holder.face.setText("Σχήμα προσώπου: " + modelHistory.getFace());
        holder.category.setText("Είδος Εργασίας: " + modelHistory.getCategory());
        holder.material.setText("Υλικό: " + modelHistory.getMaterial());
        holder.color.setText("Χρώμα: " + modelHistory.getColor());
        holder.teeth.setText("Οδοντοστοιχία: " + modelHistory.getTeeth());
        holder.comment.setText("Σχόλια: " + modelHistory.getComment());
    }


    @Override
    public int getItemCount() {
        return mTechLists.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView lastName;
        TextView date;
        TextView gender;
        TextView face;
        TextView job;
        TextView category;
        TextView material;
        TextView color;
        TextView teeth;
        TextView comment;
        CardView cardView;
        LinearLayout container;

        public MyAdapterViewHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.firstnameHistory);
            lastName = itemView.findViewById(R.id.lastnameHistory);
            date = itemView.findViewById(R.id.dateHistory);
            gender = itemView.findViewById(R.id.genderHistory);
            face = itemView.findViewById(R.id.faceHistory);
            job = itemView.findViewById(R.id.jobGroup);
            category = itemView.findViewById(R.id.categoryHistory);
            material = itemView.findViewById(R.id.materialHistory);
            color = itemView.findViewById(R.id.colorHistory);
            teeth = itemView.findViewById(R.id.teethHistory);
            comment = itemView.findViewById(R.id.commentHistory);
            cardView = itemView.findViewById(R.id.cardView);
            container = itemView.findViewById(R.id.container);

        }
    }
}

