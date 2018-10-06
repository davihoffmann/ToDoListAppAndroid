package br.edu.unidavi.todolist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;
    private TasksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        viewModel = ViewModelProviders.of(this)
                .get(TasksViewModel.class);

        viewModel.taskLiveData.observe(this, task -> {
            if(task != null) {
                this.task = task;
                setTitle(task.getTitle() + " - " + task.getData());
            }
        });

        viewModel.success.observe(this, success -> {
            if(Boolean.TRUE.equals(success)) {
                finish();
            }
        });

        int id = getIntent().getIntExtra("id", 0);
        //task = TasksStore.getInstance(getApplicationContext()).getTasksDao().findById(id);
        viewModel.findTaskById(id);

        //sem o room
        //task = getIntent().getParcelableExtra("task");
        //setTitle(task.getTitle() + " - " + task.getData());

        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TasksStore.getInstance(getApplicationContext()).getTasksDao().delete(task);
                viewModel.delete(task);
            }
        });

        Button buttonUpdate = findViewById(R.id.button_done);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TasksStore.getInstance(getApplicationContext()).getTasksDao().update(new Task(task.getId(), task.getData(), task.getTitle(), true));
                viewModel.update(new Task(task.getId(), task.getData(), task.getTitle(), true));
            }
        });
    }
}
