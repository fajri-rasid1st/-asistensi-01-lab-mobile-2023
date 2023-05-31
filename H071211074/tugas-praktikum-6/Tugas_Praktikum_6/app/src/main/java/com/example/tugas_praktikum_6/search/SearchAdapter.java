package com.example.tugas_praktikum_6.search;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_praktikum_6.R;
import com.example.tugas_praktikum_6.data.model.User;
import com.example.tugas_praktikum_6.profile.ProfileActivity;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<User> users = new ArrayList<>();

    public void setUsers(ArrayList<User> users) {
        if (this.users.size() > 0) this.users.clear();
        this.users.addAll(users);
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.ivProfil.setImageResource(user.getProfilePicture());
        holder.tvName.setText(user.getFullname());
        holder.tvUsername.setText(user.getUsername());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.ivProfil.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER, user);
            holder.ivProfil.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivProfil;
        TextView tvName, tvUsername;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfil = itemView.findViewById(R.id.iv_profil);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUsername = itemView.findViewById(R.id.tv_username);

        }
    }
}
