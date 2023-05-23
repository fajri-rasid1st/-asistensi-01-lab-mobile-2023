package com.example.tugas_praktikum_6.home;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_praktikum_6.data.model.User;
import com.example.tugas_praktikum_6.profile.ProfileActivity;
import com.example.tugas_praktikum_6.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ArrayList<User> users;

    public ItemAdapter (ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_grid_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivPhoto.setImageURI(users.get(position).getPost().getPhoto());
        holder.tvCaption.setText(users.get(position).getPost().getCaption());
        holder.tvName.setText(users.get(position).getFullname());
        holder.tvUsername.setText(users.get(position).getUsername());
        holder.ivProfil.setImageResource(users.get(position).getProfilePicture());

        holder.itemHeader.setOnClickListener(v -> {
            Intent intent = new Intent(holder.ivProfil.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER, users.get(position));
            holder.ivPhoto.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        ShapeableImageView ivProfil;
        TextView tvName, tvUsername, tvCaption;
        LinearLayout itemHeader;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            ivProfil = itemView.findViewById(R.id.iv_profil);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUsername = itemView.findViewById(R.id.tv_username);
            itemHeader = itemView.findViewById(R.id.item_header);
        }
    }


}
