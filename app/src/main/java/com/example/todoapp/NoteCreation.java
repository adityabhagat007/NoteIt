package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NoteCreation extends AppCompatActivity {

    private EditText titleEditText, contentEditText;
    TextView title_note;
    private ImageView saveNote;
    Boolean editMode = false;
    String title, content ,docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creation);
        saveNote = findViewById(R.id.saveNote);
        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        title_note =findViewById(R.id.title_note);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        docId = intent.getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()) {
            editMode = true;
            titleEditText.setText(title);
            contentEditText.setText(content);
            title_note.setText("Edit your note");
        }




        saveNote.setOnClickListener((v)->savingNotes());

    }
    void savingNotes(){
        String  title = titleEditText.getText().toString().trim();
        String  content = contentEditText.getText().toString().trim();

        if(title.isEmpty()){
            Utility.showToastShort(NoteCreation.this,"Title is Invalid");
            return;
        }
        Notes note = new Notes();
        note.setContent(content);
        note.setTimestamp(Timestamp.now());
        note.setTitle(title);

        saveNotesToFirebase(note);
    }
    void saveNotesToFirebase(Notes note){
        Db db = new Db();
        DocumentReference documentReference;
        if(editMode ==true){
            documentReference = db.getNotesCollection().document(docId);
        }else{
            documentReference = db.getNotesCollection().document();
        }
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Utility.showToastShort(NoteCreation.this,"Note add Successfully");
                }else{
                    Utility.showToastShort(NoteCreation.this,"Failed to add Note");
                }
            }
        });
    }
}