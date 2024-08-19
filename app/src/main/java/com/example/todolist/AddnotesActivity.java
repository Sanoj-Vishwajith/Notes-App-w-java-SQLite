package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.Database.Note;
import com.example.todolist.Database.NoteDAO;

import java.util.Date;

public class AddnotesActivity extends AppCompatActivity {

    Button btn_addNote;
    EditText editTitle;
    EditText editDesc;

    NoteDAO noteDAO;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnotes);

        noteDAO = new NoteDAO(this);
        noteDAO.open();
        Intent intent = new Intent(this, NotelistActivity.class);

        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        btn_addNote = findViewById(R.id.btn_addNote);

        btn_addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create a note--------------------------------------------------------------------------
                Note note;
                try {
                    String date = new Date().toString();
                    String title = editTitle.getText().toString();
                    String body = editDesc.getText().toString();
                    noteDAO.createNote(title,body,date);
                    Toast.makeText(AddnotesActivity.this, "ah weda weda", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
                catch (Exception e){
                    Toast.makeText(AddnotesActivity.this, "Error, aye blpn", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
