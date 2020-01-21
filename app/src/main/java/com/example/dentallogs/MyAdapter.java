package com.example.dentallogs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {
    List<ListItem> listItemsArrayList;
    Context context;

    public MyAdapter(List<ListItem> listItemsArrayList, Context context){
        this.listItemsArrayList = listItemsArrayList
    }
    @NonNull
    @Override
    public MyAdapter.MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyAdapterViewHolder extends RecyclerView.ViewHolder

    {


        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
