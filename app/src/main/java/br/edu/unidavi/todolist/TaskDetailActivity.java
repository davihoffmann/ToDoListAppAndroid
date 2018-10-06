package br.edu.unidavi.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskDetailActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        helper = new DatabaseHelper(getApplicationContext());

        task = getIntent().getParcelableExtra("task");
        setTitle(task.getTitle());

        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteTask(task);
                finish();
            }
        });

        Button buttonUpdate = findViewById(R.id.button_done);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.markTaskAsDone(task);
                finish();
            }
        });
    }
}
