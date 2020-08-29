package com.sebix.android_sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sebix.android_sqlite.models.Note;
import com.sebix.android_sqlite.persistance.NoteRepository;
import com.sebix.android_sqlite.util.Utility;

public class NoteActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnClickListener,
        TextWatcher {
    public static final String TAG = "NoteActivity";
    public static final int EDIT_MODE_ENABLED = 1;
    public static final int EDIT_MODE_DISABLED = 0;
    //ui components
    private LineEditText mLineEditText;
    private EditText mEditTitle;
    private TextView mTextTitle;
    private RelativeLayout mCheckContainer, mBackArrowContainer;
    private ImageButton mCheck, mBackArrow;
    //vards
    private Note note;
    private boolean mIsNewNote;
    private GestureDetector mGestureDetector;
    private int mMode;
    private NoteRepository mNoteRepository;
    private Note finalNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mLineEditText = findViewById(R.id.edit_note);
        mEditTitle = findViewById(R.id.note_edit_title);
        mTextTitle = findViewById(R.id.note_text_title);
        View view;
        mCheckContainer = findViewById(R.id.check_container);
        mBackArrowContainer = findViewById(R.id.back_arrow_container);
        mCheck = findViewById(R.id.toolbar_check);
        mBackArrow = findViewById(R.id.toolbar_back_arrow);
        mNoteRepository = new NoteRepository(this);
        if (getIncomintIntent()) {
            //edit note mode
            setNewNote();
            enableEditMode();
        } else {
            //new note mode
            setEditNote();
            disableContentInteraction();
        }
        setListeners();
    }

    private boolean getIncomintIntent() {
        if (getIntent().hasExtra("note")) {
            mMode = EDIT_MODE_DISABLED;
            note = getIntent().getParcelableExtra("note");
            finalNote = getIntent().getParcelableExtra("note");
            mIsNewNote=false;
            return false;
        }
        mIsNewNote = true;
        mMode = EDIT_MODE_ENABLED;
        return true;
    }

    private void saveChanges(){

        if(mIsNewNote){
            saveNewNote();
        }else {

        }
    }

    private void saveNewNote(){
        mNoteRepository.insertNote(finalNote);
    }

    private void enableEditMode() {
        mBackArrowContainer.setVisibility(View.GONE);
        mCheckContainer.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.VISIBLE);
        mTextTitle.setVisibility(View.GONE);
        mMode = EDIT_MODE_ENABLED;
        enableContentInteraction();
    }

    private void disableEditMode() {
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mCheckContainer.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.GONE);
        mTextTitle.setVisibility(View.VISIBLE);
        mMode = EDIT_MODE_DISABLED;
        disableContentInteraction();
        String temp = mLineEditText.getText().toString();
        temp = temp.replace("\n","");
        temp = temp.replace(" ","");
        if(temp.length()>0){
            finalNote.setTitle(mEditTitle.getText().toString());
            finalNote.setContent(mLineEditText.getText().toString());
            String timestamp = Utility.getCurrentTimestamp();
            finalNote.setTimestamp(timestamp);
            if(!finalNote.getContent().equals(note.getContent())
            ||!finalNote.getTitle().equals(note.getTitle())){
                saveChanges();
            }
        }
        //saveChanges();
    }

    private void setEditNote() {
        mTextTitle.setText(note.getTitle());
        mEditTitle.setText(note.getTitle());
        mLineEditText.setText(note.getContent());
    }

    private void setNewNote() {
        mTextTitle.setText("Note title");
        mEditTitle.setText("Note title");
        note=new Note();
        finalNote= new Note();
        note.setTitle("Note title");
     //   finalNote.setTitle("Note title");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private void setListeners() {
        mLineEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);
        mTextTitle.setOnClickListener(this);
        mCheck.setOnClickListener(this);
        mBackArrow.setOnClickListener(this);
        mEditTitle.addTextChangedListener(this);
    }

    private void disableContentInteraction(){
        mLineEditText.setKeyListener(null);
        mLineEditText.setFocusable(false);
        mLineEditText.setFocusableInTouchMode(false);
        mLineEditText.setCursorVisible(false);
        mLineEditText.clearFocus();
    }

    private void enableContentInteraction(){
        mLineEditText.setKeyListener(new EditText(this).getKeyListener());
        mLineEditText.setFocusable(true);
        mLineEditText.setFocusableInTouchMode(true);
        mLineEditText.setCursorVisible(true);
        mLineEditText.requestFocus();
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
        enableEditMode();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_check: {
                hideKeyboard(NoteActivity.this);
                disableEditMode();
                break;
            }
            case R.id.note_text_title: {
                enableEditMode();
                mEditTitle.requestFocus();
                mEditTitle.setSelection(mEditTitle.length());
                break;
            }
            case R.id.toolbar_back_arrow: {
                finish();
                break;
            }
        }
    }

    public static void hideKeyboard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if(view==null){
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    @Override
    public void onBackPressed() {
        if (mMode == EDIT_MODE_ENABLED) {
            onClick(mCheck);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mode",mMode);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMode=savedInstanceState.getInt("mode");
        if(mMode == EDIT_MODE_ENABLED){
            enableEditMode();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mTextTitle.setText(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}