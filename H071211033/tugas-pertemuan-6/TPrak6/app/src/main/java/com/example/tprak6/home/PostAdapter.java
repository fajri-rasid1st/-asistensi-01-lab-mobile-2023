package com.example.tprak6.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprak6.profile.ProfileViewerActivity;
import com.example.tprak6.R;
import com.example.tprak6.data.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final ArrayList<User> users;

    public PostAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = users.get(position);

        holder.tvName.setText(user.getName());
        holder.tvUsername.setText(user.getUsername());
        holder.circleImageView.setImageResource(user.getProfilePic());

        holder.ivPost.setImageURI(user.getPost().getImgaeUri());
        holder.tvCaption.setText(user.getPost().getCaption());

        holder.circleImageView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ProfileViewerActivity.class);

            intent.putExtra("user", user);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        ImageView ivPost;
        TextView tvName, tvUsername, tvCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circleImageView);
            ivPost = itemView.findViewById(R.id.ivPost);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
        }
    }
}
