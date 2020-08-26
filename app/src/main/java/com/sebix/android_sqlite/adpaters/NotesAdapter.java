package com.sebix.android_sqlite.adpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sebix.android_sqlite.R;
import com.sebix.android_sqlite.models.Note;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    int animation;
    ArrayList<Note> mNotesList = new ArrayList<>();

    public NotesAdapter(ArrayList<Note> mNotesList, int animation) {
        this.mNotesList = mNotesList;
        this.animation = animation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mNoteTitle.setText(mNotesList.get(position).getTitle());
        holder.mNoteTimestamp.setText(mNotesList.get(position).getTimestamp());
        setFadeAnimation(holder.itemView, animation);
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNoteTitle, mNoteTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNoteTitle = itemView.findViewById(R.id.note_title);
            mNoteTimestamp = itemView.findViewById(R.id.note_timestamp);
        }
    }

    private void setFadeAnimation(View view, int animation) {
        Animation anim = AnimationUtils.loadAnimation(view.getContext(), animation);
        view.startAnimation(anim);
    }
}
