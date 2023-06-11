package com.example.background_thread_assignment.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.background_thread_assignment.R;
import com.example.background_thread_assignment.data.model.User;
import com.example.background_thread_assignment.profile.ProfileActivity;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    ArrayList<User> users;

    public PostAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        holder.textUsername.setText(users.get(position).getUsername());
        holder.textFullname.setText(users.get(position).getFullname());
        holder.caption.setText(users.get(position).getPost().getCaption());
        holder.photoUpload.setImageURI(users.get(position).getPost().getPhoto());
        holder.photoProfile.setImageResource(users.get(position).getProfilePicture());

        holder.photoProfile.setOnClickListener(view -> {
            Intent intent = new Intent(holder.photoProfile.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER, users.get(position));
            holder.photoProfile.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textUsername, textFullname, caption;
        ImageView photoUpload;
        ShapeableImageView photoProfile;
        LinearLayout itemHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textUsername = itemView.findViewById(R.id.textUsername);
            textFullname = itemView.findViewById(R.id.textFullname);
            caption = itemView.findViewById(R.id.caption);
            photoUpload = itemView.findViewById(R.id.photoUpload);
            photoProfile = itemView.findViewById(R.id.photoProfile);
            itemHeader = itemView.findViewById(R.id.itemHeader);
        }
    }
}

