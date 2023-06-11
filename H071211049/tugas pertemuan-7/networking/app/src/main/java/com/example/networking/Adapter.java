package com.example.networking;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    private  List<User> users;

    public Adapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.uname.setText(user.getFname() + " " + user.getLname());
        holder.mail.setText(user.getEmail());
        Glide.with(holder.itemView.getContext())
                .load(user.getPictUrl())
                .into(holder.pict);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), TampilanProfilActivity.class);
            intent.putExtra(TampilanProfilActivity.EXTRA_USER, user.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pict;
        public TextView uname, mail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pict = itemView.findViewById(R.id.pict);
            uname = itemView.findViewById(R.id.uname);
            mail = itemView.findViewById(R.id.mail);
        }
    }
}

