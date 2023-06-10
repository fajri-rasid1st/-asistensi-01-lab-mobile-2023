package com.example.assignment10localdatapersistent.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment10localdatapersistent.Activity.AddNoteActivity;
import com.example.assignment10localdatapersistent.Model.NoteModel;
import com.example.assignment10localdatapersistent.Model.NoteModel;
import com.example.assignment10localdatapersistent.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<NoteModel> NoteModels;
    private ActivityResultLauncher<Intent> resultLauncher;

    public NoteAdapter(ArrayList<NoteModel> NoteModels, ActivityResultLauncher<Intent> resultLauncher) {
        this.NoteModels = NoteModels;
        this.resultLauncher = resultLauncher;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        NoteModel noteModel = NoteModels.get(position);
        holder.itemTitle_tv.setText(noteModel.getName());
        holder.createdDate_tv.setText(noteModel.getCreatedDate());
        holder.timeDate_tv.setText(noteModel.getCreatedTime());
        holder.description_tv.setText(noteModel.getDescription());

        holder.item_cv.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddNoteActivity.class);
            intent.putExtra(AddNoteActivity.EXTRA_ITEM, noteModel);
            resultLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        return NoteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle_tv, createdDate_tv, timeDate_tv, description_tv;
        CardView item_cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle_tv = itemView.findViewById(R.id.tvTitle);
            createdDate_tv = itemView.findViewById(R.id.tvTime);
            timeDate_tv = itemView.findViewById(R.id.tvDate);
            description_tv = itemView.findViewById(R.id.tvNoteDesc);
            item_cv = itemView.findViewById(R.id.cardview);
        }
    }
}