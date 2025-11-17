package com.taskflow.infrastructure.repository;

import com.taskflow.infrastructure.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
