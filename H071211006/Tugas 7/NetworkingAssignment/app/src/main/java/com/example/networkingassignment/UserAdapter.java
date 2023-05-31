package com.example.networkingassignment;

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
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class
UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    private List<UserResponse> userList;


    public UserAdapter(Context context, List<UserResponse> listUser) {
        this.context = context;
        this.userList = listUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserResponse user = userList.get(position);
        String name = user.getFirstName() + " " + user.getLastName();
        holder.tvName.setText(name);
        holder.tvEmail.setText(user.getEmail());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), UserActivity.class);
            intent.putExtra(UserActivity.EXTRA_USER, user.getId());
            holder.itemView.getContext().startActivity(intent);
        });
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatarUrl())
                .apply(new RequestOptions().override(300, 300))
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvEmail;
        private ImageView ivAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            ivAvatar = itemView.findViewById(R.id.iv_profile);
        }
    }
}