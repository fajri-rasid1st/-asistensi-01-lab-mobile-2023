package com.example.tprak7;

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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final List<UserResponse> userResponses;

    public UserAdapter(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_users, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserResponse user = userResponses.get(position);

        holder.tvFullName.setText(user.getFirstName() + " " + user.getLastName());
        holder.tvEmail.setText(user.getEmail());
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatarUrl())
                .into(holder.civUserPic);

        holder.materialCardView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ProfileViewerActivity.class);

            intent.putExtra("id", user.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userResponses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        MaterialCardView materialCardView;
        TextView tvFullName, tvEmail;
        CircleImageView civUserPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            materialCardView = itemView.findViewById(R.id.mcvUser);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            civUserPic = itemView.findViewById(R.id.civUserPic);
        }
    }
}
