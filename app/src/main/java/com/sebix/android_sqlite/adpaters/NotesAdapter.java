package com.sebix.android_sqlite.adpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sebix.android_sqlite.R;
import com.sebix.android_sqlite.models.Note;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    ArrayList<Note> mNotesList = new ArrayList<>();

    public NotesAdapter(ArrayList<Note> mNotesList) {
        this.mNotesList = mNotesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mNoteTitle.setText(mNotesList.get(position).getTitle());
        holder.mNoteTimestamp.setText(mNotesList.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoteTitle, mNoteTimestamp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNoteTitle=itemView.findViewById(R.id.note_title);
            mNoteTimestamp=itemView.findViewById(R.id.note_timestamp);
        }
    }
}
