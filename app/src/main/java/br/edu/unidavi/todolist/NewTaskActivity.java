package br.edu.unidavi.todolist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    private TasksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> {
            EditText inputNewTask = findViewById(R.id.input_new_task);
            String value = inputNewTask.getText().toString();

            if(!value.isEmpty()) {
                //Salvar no Banco de Dados!
                //TasksStore.getInstance(getApplicationContext()).getTasksDao().insert(new Task(value));
                viewModel.insert(new Task(value));
                finish();
            }
        });
    }
}