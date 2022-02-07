package com.sahinkemal.todoapp.Entity;

public class TodoCreate {
    private String title;
    private String content;
    private Boolean isDone;

    public TodoCreate() {
    }

    public TodoCreate(String title, String content, Boolean isDone) {
        this.title = title;
        this.content = content;
        this.isDone = isDone;
    }

    public Todo toTodo() {
        return new Todo(this.getTitle(), this.getContent(), this.getDone());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}


