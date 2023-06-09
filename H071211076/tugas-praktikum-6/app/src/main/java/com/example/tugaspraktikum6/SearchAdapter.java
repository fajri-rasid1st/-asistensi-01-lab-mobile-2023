package com.example.tugaspraktikum6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum6.databinding.ItemCardSearchBinding;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CardViewHolder>{

    private ArrayList<User> user;

    public SearchAdapter(ArrayList<User> user) {
        this.user = user;
    }


    @NonNull
    @Override
    public SearchAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardSearchBinding binding = ItemCardSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchAdapter.CardViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.CardViewHolder holder, int position) {
        User user = this.user.get(position);
        holder.binding.tvUsername.setText(user.getUsername());
        holder.binding.tvNickname.setText(user.getNickname());
        holder.binding.ivProfile.setImageURI(user.getImageUri());
        holder.binding.profileUser.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra("data", user);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
      ItemCardSearchBinding binding;
        public CardViewHolder(@NonNull ItemCardSearchBinding itemView) {
            super(itemView.getRoot());
               this.binding = itemView;
        }
    }
}

