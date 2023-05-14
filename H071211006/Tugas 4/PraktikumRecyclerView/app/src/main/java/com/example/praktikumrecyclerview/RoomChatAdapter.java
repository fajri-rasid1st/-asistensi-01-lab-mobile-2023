package com.example.praktikumrecyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomChatAdapter extends RecyclerView.Adapter<RoomChatAdapter.RoomChatViewHolder> {
    private ArrayList<Chat> listChat;
    public RoomChatAdapter(ArrayList<Chat> listChat) {
        this.listChat = listChat;
    }

    @NonNull
    @Override
    public RoomChatAdapter.RoomChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roomChatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_chat, parent, false);
        RoomChatAdapter.RoomChatViewHolder roomChatViewHolder = new RoomChatAdapter.RoomChatViewHolder(roomChatView);
        return roomChatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomChatAdapter.RoomChatViewHolder holder, int position) {
        Chat chat = listChat.get(position);

        holder.tvLastChat.setText(chat.getLastChat());
        holder.tvDateOfChat.setText(chat.getDateOfChat());

//        holder.itemView.setOnClickListener(view -> {
//            String name = listChat.get(holder.getAdapterPosition()).getName();
//            String phone = listChat.get(holder.getAdapterPosition()).getPhone();
//            String status = listChat.get(holder.getAdapterPosition()).getStatus();
//            String dateOfStatus = listChat.get(holder.getAdapterPosition()).getDateOfStatus();
//            String photo = listChat.get(holder.getAdapterPosition()).getPhoto();
//
//            Intent intent = new Intent(holder.itemView.getContext(), RoomChatActivity.class);
//            intent.putExtra("varName", name);
//            intent.putExtra("varPhone", phone);
//            intent.putExtra("varStatus", status);
//            intent.putExtra("varDateOfStatus", dateOfStatus);
//            intent.putExtra("varPhoto", photo);
//
//            holder.itemView.getContext().startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return listChat.size();
    }

    public class RoomChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvLastChat, tvDateOfChat, tvStatus, tvDateOfStatus;
        ImageView ivPhoto;

        public RoomChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLastChat = itemView.findViewById(R.id.tv_lastChat);
            tvDateOfChat = itemView.findViewById(R.id.tv_dateOfChat);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvDateOfStatus = itemView.findViewById(R.id.tv_dateOfStatus);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
        }
    }
}
