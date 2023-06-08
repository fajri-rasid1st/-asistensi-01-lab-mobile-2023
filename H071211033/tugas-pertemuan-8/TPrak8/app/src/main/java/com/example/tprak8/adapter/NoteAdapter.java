package com.example.tprak8.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprak8.NoteActivity;
import com.example.tprak8.R;
import com.example.tprak8.model.NoteModel;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    ArrayList<NoteModel> notes;
    ActivityResultLauncher<Intent> resultLauncher;

    public NoteAdapter(ArrayList<NoteModel> notes, ActivityResultLauncher<Intent> resultLauncher) {
        this.notes = notes;
        this.resultLauncher = resultLauncher;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NoteModel note = notes.get(position);

        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());
        holder.tvDate.setText(note.getDate());

        // intent objek note ke NoteActivity dengan ActivityResultLauncher
        holder.materialCardView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), NoteActivity.class);

            intent.putExtra(NoteActivity.EXTRA_NOTE, note);
            resultLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView materialCardView;
        MaterialTextView tvTitle, tvDescription, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            materialCardView = itemView.findViewById(R.id.mcv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
