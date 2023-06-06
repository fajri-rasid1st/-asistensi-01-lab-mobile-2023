package com.example.kuisnetworking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kuisnetworking.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<UserResponse> users;
    private Context context;

    public UserAdapter(List<UserResponse> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        UserResponse userResponse = users.get(position);
        String fullName = userResponse.getFirstName() + " " + userResponse.getLastName();
        holder.tvName.setText(fullName);
        holder.tvEmail.setText(userResponse.getEmail());
        Glide.with(context)
                .load(userResponse.getAvatarUrl())
                .into(holder.ivAvatar);
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserResponse userResponse = users.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("avatarUrl", userResponse.getAvatarUrl());
                intent.putExtra("fullName", userResponse.getFirstName() + " " + userResponse.getLastName());
                intent.putExtra("email", userResponse.getEmail());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private CardView itemCard;
        private TextView tvName, tvEmail;
        private ImageView ivAvatar;
        private ProgressBar progressBar;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            itemCard = itemView.findViewById(R.id.item_user);
        }
    }
}
