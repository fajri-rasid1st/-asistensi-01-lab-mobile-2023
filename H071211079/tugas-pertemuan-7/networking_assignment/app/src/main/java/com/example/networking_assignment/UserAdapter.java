package com.example.networking_assignment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.networking_assignment.models.User;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        Glide.with(holder.itemView.getContext()).load(user.getAvatar()).into(holder.ivProfil);
        holder.tvFullname.setText(user.getFullname());
        holder.tvEmail.setText(user.getEmail());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER, user.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivProfil;
        TextView tvFullname;
        TextView tvEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfil = itemView.findViewById(R.id.profilePict);
            tvFullname = itemView.findViewById(R.id.text_name);
            tvEmail= itemView.findViewById(R.id.text_email);
        }
    }
}
