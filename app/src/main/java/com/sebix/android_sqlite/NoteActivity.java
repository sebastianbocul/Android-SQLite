package com.sebix.android_sqlite;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.sebix.android_sqlite.models.Note;

public class NoteActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    //ui components
    private LineEditText mLineEditText;
    private EditText mEditText;
    private TextView mTextView;

    //vards
    private Note note;
    private  boolean mIsNewNote;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mLineEditText = findViewById(R.id.edit_note);
        mEditText = findViewById(R.id.note_edit_title);
        mTextView = findViewById(R.id.note_text_title);
        View view;

      if(getIncomintIntent()){
          //edit note mode
          setEditNote();
      }else {
          //new note mode
          setNewNote();
      }

        Toast.makeText(this, "note" + note, Toast.LENGTH_SHORT).show();
        setListeners();

    }

    private boolean getIncomintIntent() {
        if (getIntent().hasExtra("note")) {
            note = getIntent().getParcelableExtra("note");
            return false;
        }
        return true;
    }


    private void setEditNote(){
        mTextView.setText(note.getTitle());
        mEditText.setText(note.getTitle());
        mLineEditText.setText(note.getContent());

    }

    private void setNewNote(){
        mTextView.setText("Note title");
        mEditText.setText("Note title");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
    private void setListeners(){
        mLineEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this,this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Toast.makeText(this, "onDoubleTap", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}