package com.example.shchat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<ViewHolder>{
   private ArrayList<User>users;
   private ArrayList<ModelChat>modelChats;
    public MainAdapter(ArrayList<User> user,ArrayList<ModelChat>modelChats){
        this.users = user;
        this.modelChats = modelChats;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userview = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_design, parent, false);
        //--
        ViewHolder userViewHolder = new ViewHolder(userview);

        //---
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        ModelChat modelChat = modelChats.get(position);
        holder.imageView.setImageResource(user.getProfile());
        holder.textView.setText(user.getName());

        String message = modelChat.getMessage();
        if (message.length() > 35) {
            message = message.substring(0, 35) + "...";
        }

        holder.textView3.setText(message);
        holder.textView2.setText(modelChat.getWaktu());
        holder.profile.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),ChatActivity.class);
            intent.putExtra("Profile",user);
            view.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    //--
     ImageView imageView;
     TextView textView;
     TextView textView2;
     TextView textView3;
     RelativeLayout profile;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.imageview1);
        textView=itemView.findViewById(R.id.textview);
        textView2=itemView.findViewById(R.id.textview2);
        textView3=itemView.findViewById(R.id.textview3);
        profile=itemView.findViewById(R.id.profile);

    }
}

