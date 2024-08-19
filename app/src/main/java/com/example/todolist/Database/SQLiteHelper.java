package com.example.todolist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String NOTES_TABLE = "Notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_DATE = "date";
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, "notesDB", null,1);
    }

    //runs when first time the db calls
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "
                + NOTES_TABLE + "(" + COLUMN_ID
                + " integer PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_BODY + " TEXT NOT NULL, "
                + COLUMN_DATE + " TEXT NOT NULL);";

        db.execSQL(createTableStatement);
    }

    //runs when the db version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        onCreate(db);
    }

    //Making a single note here
//    public boolean addOneNote(Note note) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues CV = new ContentValues();
//
//        CV.put(COLUMN_TITLE, note.getTitle());
//        CV.put(COLUMN_BODY, note.getBody());
//        CV.put(COLUMN_DATE, note.getDate());
//
//        //confirming data
//        long insert = db.insert(NOTES_TABLE, null, CV);
//        if (insert==-1) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
}
