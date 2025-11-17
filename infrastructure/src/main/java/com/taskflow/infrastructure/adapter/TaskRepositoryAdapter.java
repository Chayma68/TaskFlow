package com.taskflow.infrastructure.adapter;

import com.taskflow.infrastructure.entity.TaskEntity;
import com.taskflow.infrastructure.mapper.TaskEntityMapper;
import com.taskflow.infrastructure.repository.TaskRepository;
import com.taskflow.model.Task;
import com.taskflow.model.port.TaskRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskRepository taskRepository;
    private final TaskEntityMapper taskEntityMapper;

    public TaskRepositoryAdapter(TaskRepository taskRepository,
                                 TaskEntityMapper taskEntityMapper) {
        this.taskRepository = taskRepository;
        this.taskEntityMapper = taskEntityMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = taskEntityMapper.toEntity(task);
        TaskEntity saved = taskRepository.save(entity);
        return taskEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskEntityMapper::toDomain);
    }

    @Override
    public List<Task> findByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(taskEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findByAssigneeId(Long userId) {
        return taskRepository.findByAssigneeId(userId)
                .stream()
                .map(taskEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
