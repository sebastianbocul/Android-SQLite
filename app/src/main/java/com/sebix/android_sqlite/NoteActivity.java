package com.sebix.android_sqlite;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sebix.android_sqlite.models.Note;

public class NoteActivity extends AppCompatActivity {
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        if (getIntent().hasExtra("note")) {
            note = getIntent().getParcelableExtra("note");
        }
        Toast.makeText(this, "note" + note, Toast.LENGTH_SHORT).show();
    }
}