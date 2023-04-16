package com.example.assignment6;

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

import java.util.ArrayList;

//Adapter mengambil data dari model dan menampilkannya dalam bentuk item di dalam RecyclerView
//Adapter juga menangani interaksi pengguna dengan setiap item di dalam RecyclerView
public class AdapterMainPage extends RecyclerView.Adapter<AdapterMainPage.ViewHolder> {
    private final ArrayList<ModelContact> contacts;

    //clicklistener stelah itu buat interfacenya
    private ClickListener clickListener;

    //samakan nama constructor dgn nama class
    public AdapterMainPage(ArrayList<ModelContact> contacts) {
        this.contacts = contacts;
    }

    //setter for clickListener (hanya butuh setter)
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AdapterMainPage.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ini diisi
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelContact contact = contacts.get(position);

        //ini untuk untuk bag. photoDetails
        holder.image.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), PhotoDetails.class);
            intent.putExtra(ChatActivity.EXTRA_CHAT, contact);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.setData(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    //class for viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        //bag. layout

        //atribut
        private TextView tvName, tvLastChat, tvTime;
        private ImageView image;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //tambah
            tvName = itemView.findViewById(R.id.tvName);
            tvLastChat = itemView.findViewById(R.id.tvLastChat);
            tvTime = itemView.findViewById(R.id.tvTime);
            image = itemView.findViewById(R.id.profilePic);

            itemView.setOnClickListener(view -> {
                clickListener.onItemClicked(contacts.get(getAdapterPosition()));
            });
        }

        public void setData(ModelContact thisContact) {
            //set text mainpage
            tvName.setText(thisContact.getName());
            tvLastChat.setText(thisContact.getChats().get(thisContact.getChats().size()-1).getChat());
            tvTime.setText(thisContact.getChats().get(thisContact.getChats().size()-1).getTime());

            Glide.with(itemView.getContext()).load(thisContact.getPict()).apply(new RequestOptions().override(350, 550)).into(image);
        }
    }

    interface ClickListener {
        void onItemClicked(ModelContact thisContact);
    }
}
