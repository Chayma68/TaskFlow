package com.taskflow.infrastructure.repository;

import com.taskflow.infrastructure.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByProjectId(Long projectId);
    List<TaskEntity> findByAssigneeId(Long userId);
}
