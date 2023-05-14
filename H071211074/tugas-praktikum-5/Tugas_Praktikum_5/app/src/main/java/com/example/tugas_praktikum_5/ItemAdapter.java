package com.example.tugas_praktikum_5;

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
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    ArrayList<ItemModel> listData;

    public ItemAdapter (Context context, ArrayList<ItemModel> listData) {
        this.context = context;
        this.listData = listData;
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
        holder.ivPhoto.setImageURI(listData.get(position).getPhoto());
        holder.tvCaption.setText(listData.get(position).getCaption());
        holder.tvName.setText("Wd. Ananda Lesmono");
        holder.tvUsername.setText("wd_ananda");

        holder.itemHeader.setOnClickListener(v -> {
            Intent intent = new Intent(holder.ivProfil.getContext(), ProfileActivity.class);
            holder.ivPhoto.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
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
