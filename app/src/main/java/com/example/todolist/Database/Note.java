package com.example.todolist.Database;

public class Note {

    private long id;
    private String title;
    private String body;
    private String date;

    public Note(long id, String title, String body, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public Note() {
    }

    //getters setters------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
