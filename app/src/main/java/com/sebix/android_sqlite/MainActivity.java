package com.sebix.android_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sebix.android_sqlite.adpaters.NotesAdapter;
import com.sebix.android_sqlite.models.Note;
import com.sebix.android_sqlite.persistance.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity implements
        NotesAdapter.OnNoteListener,
        FloatingActionButton.OnClickListener {
    private static final String TAG = "MainActivity";
    ArrayList<Note> mNotesList = new ArrayList<>();
    NotesAdapter mNotesAdapter;
    RecyclerView mRecyclerView;
    private NoteRepository mNoteRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        mNoteRepository=new NoteRepository(this);

        retrieveNotes();
        //  fillNotesList();
        Log.d(TAG, "onCreate: " + Thread.currentThread().getName());
        findViewById(R.id.fab).setOnClickListener(this);

        setSupportActionBar((Toolbar)findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void retrieveNotes() {
        mNoteRepository.retrieveNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if(mNotesList.size()>0){
                    mNotesList.clear();
                }
                if(notes!=null){
                    mNotesList.addAll(notes);
                }
                mNotesAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        int animation = R.anim.slide_left;
        mNotesAdapter = new NotesAdapter(mNotesList,animation,this);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        new ItemTouchHelper(itemTouchHeloperCallBack).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mNotesAdapter);
    }

    private void fillNotesList() {
        for (int i = 0; i < 100; i++) {
            Note note = new Note("Title #" + i, "", "Timestamp #" + i);
            mNotesList.add(note);
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent= new Intent(this,NoteActivity.class);
        intent.putExtra("note",mNotesList.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(this,NoteActivity.class);
        startActivity(intent);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHeloperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotesList.get(viewHolder.getAdapterPosition()),viewHolder.getAdapterPosition());
        }
    };

    private void deleteNote(Note note,int position){
        mNotesList.remove(note);
        mNotesAdapter.notifyItemRemoved(position);
        mNoteRepository.deleteNote(note);
    }
}