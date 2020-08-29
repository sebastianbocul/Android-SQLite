package com.sebix.android_sqlite.persistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.sebix.android_sqlite.models.Note;

import java.util.List;

public class NoteRepository {
    private NoteDatabase noteDatabase;

    public NoteRepository(Context context) {
        this.noteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNote(Note note){
        //noteDatabase.in
    }

    public void updateNote(Note note){

    }

    public LiveData<List<Note>> retrieveNotes(){
        return noteDatabase.getNoteDeo().getNotes();
    }

    public void deleteNote(Note note){

    }
}
