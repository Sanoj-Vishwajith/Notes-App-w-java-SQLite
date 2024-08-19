package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Database.Note;
import com.example.todolist.Database.NoteDAO;
import com.example.todolist.adapter.RecyclerAdapter;

import java.util.List;

public class NotelistActivity extends AppCompatActivity {

    Button btn_addnew;
    RecyclerView recyclerView;

    RecyclerAdapter recyclerAdapter;

    NoteDAO noteDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notelist);

        //notes list show----------------------------------------------------------------------------
        recyclerView = findViewById(R.id.listView);
        noteDAO = new NoteDAO(this);
        noteDAO.open();

        //create data on this recyler list

        //call adapter
        recyclerAdapter = new RecyclerAdapter(this);
        //set adapter
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //-------------------------------------------------------------------------------------------
        loadNote();

        btn_addnew = findViewById(R.id.btn_addnew);

        btn_addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent to move to addnotes
                Intent intent = new Intent(NotelistActivity.this, AddnotesActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noteDAO.close();
    }

    private void  loadNote(){
        List<Note> data = noteDAO.getAllNotes();
        recyclerAdapter.setData(data);
    }
}
