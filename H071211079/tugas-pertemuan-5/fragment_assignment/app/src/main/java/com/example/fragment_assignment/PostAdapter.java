package com.example.fragment_assignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<PostModel> posts;

    public PostAdapter(Context context, ArrayList<PostModel> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        holder.textUsername.setText("@dhiyaaunnisa_");
        holder.textFullname.setText("Dhiyaa Unnisa");
        holder.caption.setText(posts.get(position).getCaption());
        holder.photoUpload.setImageURI(posts.get(position).getPhoto());
        holder.photoProfile.setOnClickListener(view -> {
            Intent intent = new Intent(holder.photoProfile.getContext(), ProfileActivity.class);
            holder.photoProfile.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textUsername, textFullname, caption;
        ImageView photoUpload;
        ShapeableImageView photoProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textUsername = itemView.findViewById(R.id.textUsername);
            textFullname = itemView.findViewById(R.id.textFullname);
            caption = itemView.findViewById(R.id.caption);
            photoUpload = itemView.findViewById(R.id.photoUpload);
            photoProfile = itemView.findViewById(R.id.photoProfile);
        }
    }
}
