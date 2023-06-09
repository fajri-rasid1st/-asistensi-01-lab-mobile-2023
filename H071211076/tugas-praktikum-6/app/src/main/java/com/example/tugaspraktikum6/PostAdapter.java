package com.example.tugaspraktikum6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum6.databinding.ItemCardBinding;

import java.util.LinkedList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private final LinkedList<Post> posts;

    public PostAdapter(LinkedList<Post> posts) {this.posts = posts;}

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = ItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.binding.imageView.setImageURI(post.getImageUri());
        holder.binding.caption.setText(post.getCaption());
        holder.binding.tvUsername.setText(post.getUser().getUsername());
        holder.binding.tvNickname.setText(post.getUser().getNickname());
        holder.binding.ivProfile.setImageURI(post.getUser().getImageUri());
        holder.binding.clProfile.setOnClickListener(v -> {
            Intent intent = new Intent(holder.binding.clProfile.getContext(), ProfileActivity.class);
            intent.putExtra("data", post.getUser());
            holder.binding.clProfile.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private ItemCardBinding binding;

        public PostViewHolder(@NonNull ItemCardBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
