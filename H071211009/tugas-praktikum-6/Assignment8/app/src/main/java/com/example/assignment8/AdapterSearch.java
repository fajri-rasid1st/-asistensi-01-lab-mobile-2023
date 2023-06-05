package com.example.assignment8;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {

    // ArrayList (daftar) yang berisi objek-objek dari kelas PostModel
    // Arraylist yang muncul saat melakukan pencarian
    ArrayList<ModelPost> posts = new ArrayList<>();

    public void setPosts(ArrayList<ModelPost> posts) {
        // Menghapus hasil pencarian sebelumnya dan mengupdate dengan hasil yang baru (hasil yang dicari)
        if (this.posts.size() > 0) {
            this.posts.clear();
        }

        this.posts.addAll(posts);
    }

    // Membuat ViewHolder baru saat RecyclerView membutuhkannya
    @NonNull
    @Override
    public AdapterSearch.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menginflate layout item_contact ke dalam sebuah View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view); // Mengembalikan ViewHolder baru
    }

    // Menghubungkan data dengan tampilan pada ViewHolder
    @Override
    public void onBindViewHolder(@NonNull AdapterSearch.ViewHolder holder, int position) {
        ModelPost post = posts.get(position);
        holder.setData(post); // Mengatur data pada ViewHolder
    }

    // Mengembalikan jumlah item dalam daftar
    @Override
    public int getItemCount() {
        return posts.size();
    }

    // ViewHolder untuk setiap item dalam RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, username;
        ImageView contactPict;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactPict = itemView.findViewById(R.id.contactPic);
            name = itemView.findViewById(R.id.tvName);
            username = itemView.findViewById(R.id.tvUsername);
        }

        // Mengatur data pada tampilan ViewHolder
        public void setData(ModelPost post) {
            name.setText(post.getFullName());
            username.setText(post.getUserName());
            Glide.with(itemView.getContext()).load(post.getImage()).apply(new RequestOptions().override(350, 550)).into(contactPict);
        }
    }
}