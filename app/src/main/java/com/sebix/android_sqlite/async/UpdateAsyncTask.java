package com.sebix.android_sqlite.async;

import android.os.AsyncTask;
import android.util.Log;

import com.sebix.android_sqlite.models.Note;
import com.sebix.android_sqlite.persistance.NoteDao;

public class UpdateAsyncTask extends AsyncTask<Note, Void , Void> {
    private NoteDao mNoteDao;
    private final static String TAG = "InsertAsyncTask";

    public UpdateAsyncTask(NoteDao mNoteDao) {
        this.mNoteDao = mNoteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread().getName());
        mNoteDao.updateNotes(notes);
        return null;
    }
}
