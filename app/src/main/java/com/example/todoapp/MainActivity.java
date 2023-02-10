package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNotes = findViewById(R.id.addNotes);
        titleEditText = findViewById(R.id.)
        addNotes.setOnClickListener(v->addNotes());
    }

    void addNotes(){
        startActivity(new Intent(MainActivity.this,NoteCreation.class));
    }
}