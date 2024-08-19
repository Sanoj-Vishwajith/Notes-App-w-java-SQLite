package com.example.todolist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_TITLE,
            SQLiteHelper.COLUMN_BODY,
            SQLiteHelper.COLUMN_DATE
    };

    public NoteDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Note createNote(String title, String body, String date) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_TITLE, title);
        values.put(SQLiteHelper.COLUMN_BODY, body);
        values.put(SQLiteHelper.COLUMN_DATE, date);
        long insertId = database.insert(SQLiteHelper.NOTES_TABLE, null, values);
        Cursor cursor = database.query(SQLiteHelper.NOTES_TABLE,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Note newNote = cursorToNote(cursor);
        cursor.close();
        return newNote;
    }

    public void deleteNote(long id) {
        System.out.println("Note deleted with id: " + id);
        database.delete(SQLiteHelper.NOTES_TABLE,
                SQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<Note>();
        Cursor cursor = database.query(SQLiteHelper.NOTES_TABLE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();
        return notes;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getLong(0));
        note.setTitle(cursor.getString(1));
        note.setBody(cursor.getString(2));
        note.setDate(cursor.getString(3));
        return note;
    }
}


