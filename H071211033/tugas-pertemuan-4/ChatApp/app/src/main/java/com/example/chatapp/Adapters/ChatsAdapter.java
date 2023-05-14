package com.example.chatapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.Models.ChatsModel;
import com.example.chatapp.R;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsVH>{
    ArrayList<ChatsModel> chatsModelArrayList;

    public ChatsAdapter(ArrayList<ChatsModel> chatsModelArrayList) {
        this.chatsModelArrayList = chatsModelArrayList;
    }

    @NonNull
    @Override
    public ChatsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatsVH(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.chat_bubble, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsVH holder, int position) {
        ChatsModel chatsModel = chatsModelArrayList.get(position);

        holder.tvPesan.setText(chatsModel.getPesan());
        holder.tvWaktu.setText(chatsModel.getWaktu());
    }

    @Override
    public int getItemCount() {
        return chatsModelArrayList.size();
    }

}




class ChatsVH extends RecyclerView.ViewHolder {

    Context context;
    LinearLayout lyBubble;
    TextView tvPesan , tvWaktu;

    public ChatsVH(@NonNull View itemView) {
        super(itemView);

        lyBubble = itemView.findViewById(R.id.lyBubble);
        tvPesan = itemView.findViewById(R.id.tvPesan);
        tvWaktu = itemView.findViewById(R.id.tvWaktu);
    }
}