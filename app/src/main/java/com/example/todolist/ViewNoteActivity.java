package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todolist.Database.NoteDAO;

public class ViewNoteActivity extends AppCompatActivity {

    Button btn_DeleteNote;
    TextView DisplayTitle;
    TextView DispalyBody;
    TextView DisplayDate;

    NoteDAO noteDAO;

    String title,body,date;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        btn_DeleteNote = findViewById(R.id.btn_deleteNote);
        DisplayTitle = findViewById(R.id.DisplayTitle);
        DispalyBody = findViewById(R.id.DisplayBody);
        DisplayDate = findViewById(R.id.DispayDate);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        body = intent.getStringExtra("body");
        date = intent.getStringExtra("date");
        id = intent.getLongExtra("id",-1);

        DisplayDate.setText(date);
        DispalyBody.setText(body);
        DisplayTitle.setText(title);

        noteDAO = new NoteDAO(this);
        noteDAO.open();

        btn_DeleteNote.setOnClickListener(v->{
            noteDAO.deleteNote(id);
            Intent intent1 = new Intent(this,NotelistActivity.class);
            startActivity(intent1);
        });

    }
}