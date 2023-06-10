package com.example.assignment9;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment9.model.User;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {

    private List<User> userList;
    Context context;

    //constructor
    public Adapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.UserViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvName.setText(user.getFirstName() + " " + user.getLastName());
        holder.tvEmail.setText(user.getEmail());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), UserProfileActivity.class);
            intent.putExtra(UserProfileActivity.EXTRA_USER, user.getId());
            holder.itemView.getContext().startActivity(intent);
        });

        Glide.with(holder.itemView.getContext())
                .load(user.getAvatarUrl())
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvEmail;
        ImageView ivAvatar;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            ivAvatar = itemView.findViewById(R.id.profilePic);
        }
    }

}