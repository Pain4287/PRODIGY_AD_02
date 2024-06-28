package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TaskViewHolder> {

    private final List<Task> tasks;

    public Adapter(List<Task> tasks) {
        this.tasks = tasks;
    }


    static class TaskViewHolder extends RecyclerView.ViewHolder {
        EditText editTextTask;
        Button buttonEdit;
        Button buttonDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextTask = itemView.findViewById(R.id.editTextTaskItem);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.editTextTask.setText(task.getName());

        holder.buttonEdit.setOnClickListener(v -> {
            task.setName(holder.editTextTask.getText().toString());
            notifyItemChanged(position);
        });

        holder.buttonDelete.setOnClickListener(v -> {
            tasks.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, tasks.size());
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
