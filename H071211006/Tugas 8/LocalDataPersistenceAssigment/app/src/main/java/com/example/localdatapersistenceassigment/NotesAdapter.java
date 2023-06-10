package com.example.localdatapersistenceassigment;

import static com.example.localdatapersistenceassigment.FormActivity.EXTRA_NOTES;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private final ClickListener clickListener;

    private List<Notes> notesList;


    public NotesAdapter(List<Notes> notesList, ClickListener clickListener) {
        this.notesList = notesList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notes, parent, false);
        return new ViewHolder(view);
    }

    public void updateList(List<Notes> newList) {
        notesList.clear();
        notesList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes notes = notesList.get(position);
        holder.tvTitle.setText(notes.getTitle());
        holder.tvDetail.setText(notes.getDetail());
        holder.tvDesc.setText(notes.getDesc());
        holder.itemView.setOnClickListener(v-> clickListener.onItemClicked(notes));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDetail, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }

    public interface ClickListener {
        void onItemClicked(Notes note);
    }
}