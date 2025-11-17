package com.taskflow.model.port;

import com.taskflow.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(Long id);

    List<Task> findByProjectId(Long projectId);

    List<Task> findByAssigneeId(Long userId);

    List<Task> findAll();

    void deleteById(Long id);
}
