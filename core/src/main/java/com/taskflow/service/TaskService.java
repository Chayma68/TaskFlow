package com.taskflow.service;

import com.taskflow.model.Task;
import com.taskflow.model.port.TaskRepositoryPort;

import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    public Task createTask(Task task) {
        return taskRepositoryPort.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepositoryPort.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }

    public void deleteTask(Long id) {
        taskRepositoryPort.deleteById(id);
    }
}
