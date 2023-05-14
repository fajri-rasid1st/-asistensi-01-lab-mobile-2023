package com.example.assignment6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adapter mengambil data dari model dan menampilkannya dalam bentuk item di dalam RecyclerView
//Adapter juga menangani interaksi pengguna dengan setiap item di dalam RecyclerView
public class AdapterForChat extends RecyclerView.Adapter<AdapterForChat.ViewHolder> {

    ArrayList<ModelChat> chats;

    //constructor
    public AdapterForChat(ArrayList<ModelChat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public AdapterForChat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_bubble, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForChat.ViewHolder holder, int position) {
        ModelChat chat = chats.get(position);
        holder.setData(chat);
    }

    @Override
    public int getItemCount() {
        //diubah
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //make atribut
        TextView tvChat, tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChat = itemView.findViewById(R.id.tvChat);
            tvTime = itemView.findViewById(R.id.tvTime);
        }

        public void setData(ModelChat chat) {
            tvChat.setText(chat.getChat());
            tvTime.setText(chat.getTime());
        }
    }
}
