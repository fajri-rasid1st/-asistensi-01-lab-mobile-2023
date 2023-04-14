package com.example.shchat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder>{
    ArrayList<ModelChat>modelChats;
    public ChatAdapter(ArrayList<ModelChat>modelChats){

        this.modelChats = modelChats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatview = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.chat_bubble, parent, false);
        //--

        //---
        return new ChatViewHolder(chatview);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ModelChat modelChat = modelChats.get(position);
        holder.tvPesan.setText(modelChat.getMessage());
        holder.tvWaktu.setText(modelChat.getWaktu());

    }

    @Override
    public int getItemCount() {
        return modelChats.size();
    }
}
class ChatViewHolder extends RecyclerView.ViewHolder {
    TextView tvPesan;
    TextView tvWaktu;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        tvPesan = itemView.findViewById(R.id.tvPesan);
        tvWaktu = itemView.findViewById(R.id.tvWaktu);
    }
}




