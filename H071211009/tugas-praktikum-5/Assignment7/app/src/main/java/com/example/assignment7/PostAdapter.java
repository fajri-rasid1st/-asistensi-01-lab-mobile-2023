package com.example.assignment7;

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

//Adapter mengambil data dari model dan menampilkannya dalam bentuk item di dalam RecyclerView
//Adapter juga menangani interaksi pengguna dengan setiap item di dalam RecyclerView

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    //  ArrayList (daftar) yang berisi objek-objek dari kelas PostModel
    ArrayList<PostModel> posts;
    private final Context context;

    public PostAdapter(Context context, ArrayList<PostModel> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.caption.setText(posts.get(position).getCaption());
        holder.post.setImageURI(posts.get(position).getImage());
        holder.profile.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView caption;
        ImageView post, profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.tvCaption);
            post = itemView.findViewById(R.id.ivImg);
            profile = itemView.findViewById(R.id.profile);
        }
    }
}