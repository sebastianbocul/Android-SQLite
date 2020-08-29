package com.sebix.android_sqlite.persistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.sebix.android_sqlite.async.DeleteAsyncTask;
import com.sebix.android_sqlite.async.InsertAsyncTask;
import com.sebix.android_sqlite.async.UpdateAsyncTask;
import com.sebix.android_sqlite.models.Note;

import java.util.List;

public class NoteRepository {
    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context) {
        this.mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNote(Note note){
        //noteDatabase.in
        new InsertAsyncTask(mNoteDatabase.getNoteDeo()).execute(note);
    }

    public void updateNote(Note note){
        new UpdateAsyncTask(mNoteDatabase.getNoteDeo()).execute(note);
    }

    public LiveData<List<Note>> retrieveNotes(){
        return mNoteDatabase.getNoteDeo().getNotes();
    }

    public void deleteNote(Note note){
        new DeleteAsyncTask(mNoteDatabase.getNoteDeo()).execute(note);
    }
}
