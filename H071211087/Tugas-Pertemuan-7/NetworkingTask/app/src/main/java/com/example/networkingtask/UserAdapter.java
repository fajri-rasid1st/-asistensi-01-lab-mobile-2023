package com.example.networkingtask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

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
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        UserResponse userResponse = users.get(position);
        String fullName = userResponse.getFirstName() + " " + userResponse.getLastName();

        holder.tvName.setText(fullName);
        holder.tvEmail.setText(userResponse.getEmail());

        Glide.with(context)
                .load(userResponse.getAvatarUrl())
                .into(holder.ivAvatar);


        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),ProfileActivity.class);
            intent.putExtra("id",userResponse.getId());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvEmail;
        private ImageView ivAvatar;
        private MaterialCardView cardView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            ivAvatar = itemView.findViewById(R.id.iv_avatar1);
        }
    }
}
