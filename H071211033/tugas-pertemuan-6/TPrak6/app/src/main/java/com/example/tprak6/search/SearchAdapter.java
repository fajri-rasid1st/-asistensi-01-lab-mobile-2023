package com.example.tprak6.search;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprak6.R;
import com.example.tprak6.data.model.User;
import com.example.tprak6.profile.ProfileViewerActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    private final List<User> users;

    public SearchAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        User user = users.get(position);
        holder.civProfile.setImageResource(user.getProfilePic());
        holder.tvName.setText(user.getName());
        holder.tvUsername.setText(user.getUsername());

        holder.cvSearchResult.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ProfileViewerActivity.class);

            intent.putExtra("user", user);
            v.getContext().startActivity(intent);
        });

        holder.llNameUsername.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ProfileViewerActivity.class);

            intent.putExtra("user", user);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        CardView cvSearchResult;
        CircleImageView civProfile;
        TextView tvName, tvUsername;
        LinearLayout llNameUsername;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            cvSearchResult = itemView.findViewById(R.id.cvSearchResult);
            civProfile = itemView.findViewById(R.id.civProfile);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            llNameUsername = itemView.findViewById(R.id.llNameUsername);
        }
    }
}
