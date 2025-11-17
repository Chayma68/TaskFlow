package com.taskflow.infrastructure.repository;

import com.taskflow.infrastructure.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByTaskId(Long taskId);
}
