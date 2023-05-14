package com.example.rv_assignment;

import static com.example.rv_assignment.ModelClass2.LAYOUT_ONE;
import static com.example.rv_assignment.ModelClass2.LAYOUT_TWO;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter {

    List<ModelClass2> List;
    Context context;
    private String name;
    private int profilePict;

    public Adapter2(java.util.List<ModelClass2> list, Context context) {
        List = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (List.get(position).getViewType()) {
            case 1:
                return LAYOUT_ONE;

            case 2:
                return LAYOUT_TWO;

            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LAYOUT_ONE:
                View LayoutOne = LayoutInflater.from(context).inflate(R.layout.sender_message_layout,parent
                ,false);
                return new SenderMessageViewHolder(LayoutOne);

            case LAYOUT_TWO:
                View LayoutTwo = LayoutInflater.from(context).inflate(R.layout.reciever_message_layout,parent
                        ,false);
                return new RecieverMessageViewHolder(LayoutTwo);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (List.get(position).getViewType()) {
            case LAYOUT_ONE:
                String sm = List.get(position).getMessage();
                String cv = List.get(position).getTime();
                ((SenderMessageViewHolder) holder).setView(sm, cv);
                break;

            case LAYOUT_TWO:
                String rm = List.get(position).getMessage();
                String cv2 = List.get(position).getTime();
                ((RecieverMessageViewHolder) holder).setView(rm, cv2);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return List.size();
    }

    class SenderMessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_sm;
        private final TextView cv_sm;
        public SenderMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_sm = itemView.findViewById(R.id.tv_sm);
            cv_sm = itemView.findViewById(R.id.cv_sm);
        }

        private void setView(String message, String time) {
            tv_sm.setText(message);
            cv_sm.setText(time);
        }

    }

    class RecieverMessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_rm;
        private final TextView cv_rm;

        public RecieverMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rm = itemView.findViewById(R.id.tv_rm);
            cv_rm = itemView.findViewById(R.id.cv_rm);
        }

        private void setView(String message, String time) {
            tv_rm.setText(message);
            cv_rm.setText(time);
        }

    }


}
