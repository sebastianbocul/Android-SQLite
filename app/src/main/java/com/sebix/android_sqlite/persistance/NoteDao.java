package com.sebix.android_sqlite.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sebix.android_sqlite.models.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    long[] insertNotes(Note...notes);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();


    @Delete
    int deleteNotes(Note...notes);

    @Update
    int updateNotes(Note ... notes);
}
