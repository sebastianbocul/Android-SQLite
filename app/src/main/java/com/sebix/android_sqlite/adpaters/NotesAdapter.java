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
    private OnNoteListener onNoteListener;

    public NotesAdapter(ArrayList<Note> mNotesList, int animation, OnNoteListener onNoteListener) {
        this.mNotesList = mNotesList;
        this.animation = animation;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mNoteTitle.setText(mNotesList.get(position).getTitle());
        holder.mNoteTimestamp.setText(mNotesList.get(position).getTimestamp());
        setAnimation(holder.itemView, animation);
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView mNoteTitle, mNoteTimestamp;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            this.onNoteListener = onNoteListener;
            mNoteTitle = itemView.findViewById(R.id.note_title);
            mNoteTimestamp = itemView.findViewById(R.id.note_timestamp);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            setAnimation(v, R.anim.shake);
            return false;
        }
    }

    private void setAnimation(View view, int animation) {
        Animation anim = AnimationUtils.loadAnimation(view.getContext(), animation);
        view.startAnimation(anim);
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
