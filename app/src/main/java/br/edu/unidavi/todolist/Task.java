package br.edu.unidavi.todolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private final Integer id;
    private final String data;
    private final String title;
    private final boolean done;

    @Ignore
    public Task(String data, String title, boolean done) {
        this.id = null;
        this.data = data;
        this.title = title;
        this.done = done;
    }

    public Task(Integer id, String data, String title, boolean done) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }
}