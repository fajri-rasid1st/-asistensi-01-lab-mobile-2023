package com.example.simplyfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<MyItem> users = new ArrayList<MyItem>();

    public MyAdapter(ArrayList<MyItem> users){
        this.users = users;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageResource;
        public TextView caption;
        public LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageResource = itemView.findViewById(R.id.postImage);
            caption = itemView.findViewById(R.id.displayCaptionTv);
            linearLayout = itemView.findViewById(R.id.headerPost);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyItem currentItem = users.get(position);
        holder.caption.setText(currentItem.caption);
        Glide.with(holder.itemView.getContext()).load(currentItem.imgPost).into(holder.imageResource);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),PhotoProfile.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }
}
