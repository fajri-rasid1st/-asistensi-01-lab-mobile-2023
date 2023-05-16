package com.example.fragmentassigment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {
    Context context;
    private ArrayList<DataPost> dataList;

    public AdapterPost(Context context, ArrayList<DataPost> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdapterPost.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPost.ViewHolder holder, int position) {
        holder.tvCaption.setText(dataList.get(position).getCaption());
        holder.ivUpload.setImageURI(dataList.get(position).getImageUri());
        holder.ivProfile.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
        holder.tvName.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
        holder.tvUser.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCaption, tvName, tvUser;
        ImageView ivUpload, ivProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUser = itemView.findViewById(R.id.tv_user);
            ivUpload = itemView.findViewById(R.id.iv_upload);
            ivProfile = itemView.findViewById(R.id.iv_profile);
        }
    }
}
