package com.example.rv_assignment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;

    public Adapter (List<ModelClass>userList){
        this .userList=userList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        ModelClass modelClass = userList.get(position);
        int resource = modelClass.getProfilePict();
        String name = modelClass.getName();
        String msg = modelClass.getLastMessage();
        String time = modelClass.getTime();
        holder.setData(resource, name, msg, time);
        
    holder.itemView.setOnClickListener(view -> {
        Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
        intent.putExtra(MainActivity2.EXTRA_CHAT, modelClass);
        holder.itemView.getContext().startActivity(intent);
    });

    holder.imageView.setOnClickListener(view -> {
        Intent intent = new Intent(holder.itemView.getContext(), ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_CHAT, modelClass);
        holder.itemView.getContext().startActivity(intent);
    });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView1);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);

        }

        public void setData(int resource, String name, String msg, String time) {

            imageView.setImageResource(resource);
            textView1.setText(name);
            textView3.setText(msg);
            textView2.setText(time);

        }

    }


}
