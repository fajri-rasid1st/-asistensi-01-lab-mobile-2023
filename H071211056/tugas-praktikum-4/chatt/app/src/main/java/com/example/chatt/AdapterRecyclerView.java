package com.example.chatt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class AdapterRecyclerView  extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    private ArrayList<ItemModel> dataItem;

    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name1, time1, chat1;
        ImageView img1;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.img1);
            name1 = itemView.findViewById(R.id.name1);
            time1 = itemView.findViewById(R.id.time1);
            chat1 = itemView.findViewById(R.id.chat1);
            parentLayout = itemView.findViewById(R.id.parentLayout);

        }
    }


    AdapterRecyclerView(Context context, ArrayList<ItemModel> dataItem){
        this.context = context;
        this.dataItem = dataItem;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.ViewHolder holder, int position) {
        TextView name1 = holder.name1;
        TextView time1 = holder.time1;
        TextView chat1 = holder.chat1;
        ImageView img1 = holder.img1;


        name1.setText(dataItem.get(position).getName1());
        time1.setText(dataItem.get(position).getTime1());
        chat1.setText(dataItem.get(position).getChat1());
        img1.setImageResource(dataItem.get(position).getImg1());


        holder.parentLayout.setOnClickListener(v -> {
            if (dataItem.get(position).getName1().equals("Eren")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.eren);
                intent.putExtra("TEKS_DEFAULT1", "Eren");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Braun")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.braun);
                intent.putExtra("TEKS_DEFAULT1", "Braun");
                intent.putExtra("TEKS_DEFAULT2", "Damn");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Erwin Smith")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.erwinsmit);
                intent.putExtra("TEKS_DEFAULT1", "Erwin Smith");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Historia")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.historia);
                intent.putExtra("TEKS_DEFAULT1", "Historia");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Hajji")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.hajji);
                intent.putExtra("TEKS_DEFAULT1", "Hajji");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Jean")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.jean);
                intent.putExtra("TEKS_DEFAULT1", "Jean");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Mikasa")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.mikasa);
                intent.putExtra("TEKS_DEFAULT1", "Mikasa");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Sdr Eren")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.sdreren);
                intent.putExtra("TEKS_DEFAULT1", "Sdr Eren");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Levi")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.levi);
                intent.putExtra("TEKS_DEFAULT1", "Levi");
                intent.putExtra("TEKS_DEFAULT2", "halo");
                context.startActivity(intent);
            }if (dataItem.get(position).getName1().equals("Shasha")){
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("GAMBAR_DEFAULT", R.drawable.shasa);
                intent.putExtra("TEKS_DEFAULT1", "Shasha");
                intent.putExtra("TEKS_DEFAULT2","halo");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }
}
