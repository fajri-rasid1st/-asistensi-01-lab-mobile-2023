package com.example.assignment8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

//Adapter mengambil data dari model dan menampilkannya dalam bentuk item di dalam RecyclerView
//Adapter juga menangani interaksi pengguna dengan setiap item di dalam RecyclerView

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {

    // ArrayList (daftar) yang berisi objek-objek dari kelas PostModel
    ArrayList<ModelPost> posts;
    Context context;

    public AdapterPost(Context context, ArrayList<ModelPost> posts) {
        this.context = context;
        this.posts = posts;
    }

    // Membuat ViewHolder baru saat RecyclerView membutuhkannya
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menginflate layout item_upload ke dalam sebuah View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload, parent, false);
        return new ViewHolder(view); // Mengembalikan ViewHolder baru
    }

    // Menghubungkan data dengan tampilan pada ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPost post = posts.get(position);
        holder.setData(post); // Mengatur data pada ViewHolder

        // Aksi ketika profil di klik
        holder.profile.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("username", post.getUserName());
            intent.putExtra("fullname", post.getFullName());
            intent.putExtra("fotoprofile", post.getContactPicture());
            context.startActivity(intent);
        });
    }

    // Mengembalikan jumlah item dalam daftar
    @Override
    public int getItemCount() {
        return posts.size();
    }

    // ViewHolder untuk setiap item dalam RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView caption, userName, fullName;
        ImageView imagePost, profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Menginisialisasi tampilan pada ViewHolder
            caption = itemView.findViewById(R.id.tvCaption);
            imagePost = itemView.findViewById(R.id.ivImg);
            profile = itemView.findViewById(R.id.profile);
            userName = itemView.findViewById(R.id.tv_Username);
            fullName = itemView.findViewById(R.id.tv_FullName);
        }

        // Mengatur data pada tampilan ViewHolder
        public void setData(ModelPost post) {
            caption.setText(post.getCaption());
            userName.setText(post.getUserName());
            fullName.setText(post.getFullName());
            Glide.with(itemView.getContext()).load(post.getContactPicture()).apply(new RequestOptions().override(350, 550)).into(profile);
            Glide.with(itemView.getContext()).load(post.getImage()).apply(new RequestOptions().override(350, 550)).into(imagePost);
        }
    }
}