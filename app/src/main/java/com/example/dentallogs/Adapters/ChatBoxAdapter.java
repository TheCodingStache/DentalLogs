package com.example.dentallogs.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Model.MessageModel;
import com.example.dentallogs.R;

import java.util.List;

public class ChatBoxAdapter extends RecyclerView.Adapter<ChatBoxAdapter.MyViewHolder> {
    private List<MessageModel> MessageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nickname;
        public TextView message;


        public MyViewHolder(View view) {
            super(view);
            nickname = view.findViewById(R.id.nickname);
            message = view.findViewById(R.id.message);
        }
    }

    public ChatBoxAdapter(List<MessageModel> MessagesList) {
        this.MessageList = MessagesList;

    }

    @Override
    public int getItemCount() {
        return MessageList.size();
    }

    @Override
    public ChatBoxAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ChatBoxAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChatBoxAdapter.MyViewHolder holder, final int position) {
        final MessageModel messageModel = MessageList.get(position);
//        holder.nickname.setText(messageModel.getName());
        holder.message.setText(messageModel.getMessage());
    }
}