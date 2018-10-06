package br.edu.unidavi.todolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private final Integer id;
    private final Date data;
    private final String title;
    private final boolean done;

    @Ignore
    public Task(String title) {
        this.id = null;
        this.data = new Date();
        this.title = title;
        this.done = false;
    }

    public Task(Integer id, Date data, String title, boolean done) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

}