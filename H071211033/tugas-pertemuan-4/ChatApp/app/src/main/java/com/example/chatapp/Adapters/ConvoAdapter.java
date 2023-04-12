package com.example.chatapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.ChatActivity;
import com.example.chatapp.ImgViewerActivity;
import com.example.chatapp.Models.ChatsModel;
import com.example.chatapp.Models.UserModel;
import com.example.chatapp.R;

import java.util.ArrayList;

public class ConvoAdapter extends RecyclerView.Adapter<ConvoVH>{

    ArrayList<UserModel> userModelArrayList;
    ArrayList<ChatsModel> chatsModelArrayList;

    public ConvoAdapter(ArrayList<UserModel> userModelArrayList, ArrayList<ChatsModel> chatsModelArrayList) {
        this.userModelArrayList = userModelArrayList;
        this.chatsModelArrayList = chatsModelArrayList;
    }

    @NonNull
    @Override
    public ConvoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConvoVH(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.chat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConvoVH holder, int position) {
        UserModel userModel = userModelArrayList.get(position);

        holder.tvNama.setText(userModel.getNama());
        holder.ivProfil.setImageResource(userModel.getProfil());

        if (position < chatsModelArrayList.size()) {
            ChatsModel chatsModel = chatsModelArrayList.get(position);

            String message = chatsModel.getPesan();
            if (message.length() > 10) {
                message = message.substring(0, 35) + "...";
            }

            holder.tvPrev.setText(message);
            holder.tvWaktu.setText(chatsModel.getWaktu());
        } else {
            // Corresponding chatsModel does not exist, set tvPrev and tvWaktu to empty string
            holder.tvPrev.setText("");
            holder.tvWaktu.setText("");
        }

        holder.lyConvo.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ChatActivity.class);
            intent.putExtra("user", userModel);
            view.getContext().startActivity(intent);
        });

        holder.ivProfil.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ImgViewerActivity.class);
            intent.putExtra("user", userModel);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return Math.max(userModelArrayList.size(), chatsModelArrayList.size());
    }
}

class ConvoVH extends RecyclerView.ViewHolder {

    LinearLayout lyConvo;
    TextView tvNama, tvPrev, tvWaktu;
    ImageView ivProfil;

    public ConvoVH(@NonNull View itemView) {
        super(itemView);

        lyConvo = itemView.findViewById(R.id.lyConvo);
        tvNama = itemView.findViewById(R.id.tvNama);
        tvPrev = itemView.findViewById(R.id.tvPrev);
        tvWaktu = itemView.findViewById(R.id.tvWaktu);
        ivProfil = itemView.findViewById(R.id.ivFoto);
    }
}