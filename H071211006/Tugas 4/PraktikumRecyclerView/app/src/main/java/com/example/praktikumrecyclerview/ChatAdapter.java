package com.example.praktikumrecyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private ArrayList<Chat> listChat;

    public ChatAdapter(ArrayList<Chat> listChat) {
        this.listChat = listChat;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(chatView);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        Chat chat = listChat.get(position);
        holder.tvName.setText(chat.getName());
        holder.tvLastChat.setText(chat.getLastChat());
        holder.tvDateOfChat.setText(chat.getDateOfChat());

        String name = listChat.get(holder.getAdapterPosition()).getName();
        String photo = listChat.get(holder.getAdapterPosition()).getPhoto();
        String status = listChat.get(holder.getAdapterPosition()).getStatus();
        String dateOfStatus = listChat.get(holder.getAdapterPosition()).getDateOfStatus();
        String phone = listChat.get(holder.getAdapterPosition()).getPhone();
        String lastChat = listChat.get(holder.getAdapterPosition()).getLastChat();

        Glide
                .with(holder.itemView)
                .load(chat.getPhoto())
                .centerCrop()
                .into(holder.ivPhoto);

        holder.ivPhoto.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra("varName", name);
            intent.putExtra("varPhoto", photo);

            holder.itemView.getContext().startActivity(intent);
        });

        holder.itemView.setOnClickListener(view -> {


            Intent intent = new Intent(holder.itemView.getContext(), RoomChatActivity.class);

            intent.putExtra("varName", name);
            intent.putExtra("varStatus", status);
            intent.putExtra("varDateOfStatus", dateOfStatus);
            intent.putExtra("varPhoto", photo);
            intent.putExtra("varPhone", phone);
            intent.putExtra("varLastChat", lastChat);

            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listChat.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvLastChat, tvDateOfChat;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLastChat = itemView.findViewById(R.id.tv_lastChat);
            tvDateOfChat = itemView.findViewById(R.id.tv_dateOfChat);
        }
    }
}
