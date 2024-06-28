package com.example.todolist;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> taskList = new ArrayList<>();
    private Adapter taskAdapter;
    private EditText editTextTask;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);

        taskAdapter = new Adapter(taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(v -> {
            String taskName = editTextTask.getText().toString();
            if (!TextUtils.isEmpty(taskName)) {
                Task task = new Task(System.currentTimeMillis(), taskName);
                taskList.add(task);
                taskAdapter.notifyItemInserted(taskList.size() - 1);
                editTextTask.setText("");
            }
        });
    }
}
