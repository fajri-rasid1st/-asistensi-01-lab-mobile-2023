package com.example.tprak5;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

    ArrayList<Post> posts = new ArrayList<>();

    //menambahkan objek post dengan parameter post
    public void addPost(Post post) {

        posts.add(post);
        notifyItemInserted(posts.size()-1);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.ivPost.setImageURI(post.getImgaeUri());
        holder.tvCaption.setText(post.getCaption());

        //pindah activity utk melihat profil
        holder.circleImageView.setOnClickListener(v -> {
            v.getContext().startActivity(new Intent(v.getContext(), ProfileViewerActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}

class PostViewHolder extends RecyclerView.ViewHolder{

    CircleImageView circleImageView;
    ImageView ivPost;
    TextView tvCaption;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        circleImageView = itemView.findViewById(R.id.circleImageView);
        ivPost = itemView.findViewById(R.id.ivPost);
        tvCaption = itemView.findViewById(R.id.tvCaption);
    }
}
