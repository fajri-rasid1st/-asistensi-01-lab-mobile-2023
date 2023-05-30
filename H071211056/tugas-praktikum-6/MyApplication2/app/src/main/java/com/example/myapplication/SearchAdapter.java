package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.models.User;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<User> users;

    public SearchAdapter(ArrayList<User> users) {
        this.users = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullName, nickName;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.fullName);
            nickName = itemView.findViewById(R.id.nickName);
            image = itemView.findViewById(R.id.img);

        }
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        User user = users.get(position);

        holder.fullName.setText(user.getFullName());
        holder.nickName.setText(user.getUserName());
        Glide.with(holder.itemView.getContext()).load(user.getImg()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public void setFilter(List<User> filterModel){
        users = new ArrayList<>();
        users.addAll(filterModel);
        notifyDataSetChanged();
    }
}
