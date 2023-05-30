package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.models.User;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.HolderData> {

    private final ArrayList<User> users;

    public PostAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        //View view = inflater.inflate(R.layout.item_list, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        User user = users.get(position);

        holder.fullName.setText(user.getFullName());
        holder.userName.setText(user.getUserName());
        holder.caption.setText(user.getPost().getCaption());

        Glide.with(holder.itemView.getContext()).load(user.getImg()).into(holder.userPhoto);
        Glide.with(holder.itemView.getContext()).load(user.getPost().getPhotoUri()).into(holder.img);

        holder.userPhoto.setOnClickListener(view -> {
            Intent toProfile = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            toProfile.putExtra(ProfileActivity.EXTRA_USER, user);
            holder.itemView.getContext().startActivity(toProfile);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder {

        TextView fullName, userName, caption;
        ImageView img, userPhoto ;


        public HolderData(@NonNull View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.cap);
            fullName = itemView.findViewById(R.id.fullName);
            userName = itemView.findViewById(R.id.userName);
            img = itemView.findViewById(R.id.img);
            userPhoto = itemView.findViewById(R.id.userPhoto);

        }
    }
}
