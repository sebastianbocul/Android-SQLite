package com.sebix.android_sqlite;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sebix.android_sqlite.adpaters.NotesAdapter;
import com.sebix.android_sqlite.models.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> mNotesList = new ArrayList<>();
    NotesAdapter mNotesAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        fillNotesList();
        mNotesAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        mNotesAdapter = new NotesAdapter(mNotesList);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mNotesAdapter);
    }

    private void fillNotesList() {
        for (int i = 0; i < 100; i++) {
            Note note = new Note("Title #" + i, "", "Timestamp #" + i);
            mNotesList.add(note);
        }
    }
}