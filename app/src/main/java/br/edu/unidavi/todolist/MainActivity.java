package br.edu.unidavi.todolist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TasksViewModel tasksViewModel;

    private TasksAdapter adapter = new TasksAdapter(new TasksAdapter.OnTaskClickListener() {
        @Override
        public void onClick(Task task) {
            //Toast.makeText(getApplicationContext(), "Touch!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), TaskDetailActivity.class);
            intent.putExtra("id", task.getId());
            startActivity(intent);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        tasksViewModel.tasks.observe(this, tasks -> {
            if(tasks != null) {
                adapter.setup(tasks);
            }
        });

        RecyclerView taskList = findViewById(R.id.task_list);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(adapter);

        FloatingActionButton buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Click!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), NewTaskActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //List<Task> tasks = TasksStore.getInstance(this).getTasksDao().fetchTaskas();
        tasksViewModel.fetchTasks();
    }
}
