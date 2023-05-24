package com.example.backgroundthreadtask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backgroundthreadtask.data.model.User;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CardViewHolder> {
    private final ArrayList<User>users;

    public PostAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        User user = users.get(position);
        holder.tv_caption.setText(user.getPost().getCaption());
        holder.iv_postphoto.setImageURI(user.getPost().getImg_post());

        holder.tv_fullname.setText(user.getName());
        holder.tv_username.setText(user.getUsername());
        holder.iv_userphoto.setImageResource(user.getFoto());

    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView tv_username, tv_fullname, tv_caption;
        ImageView iv_userphoto, iv_postphoto;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_caption = itemView.findViewById(R.id.et_capt);
            tv_username = itemView.findViewById(R.id.textView2);
            tv_fullname = itemView.findViewById(R.id.textView);
            iv_userphoto = itemView.findViewById(R.id.profileImageView);
            iv_postphoto = itemView.findViewById(R.id.imageView);
        }
    }
}
