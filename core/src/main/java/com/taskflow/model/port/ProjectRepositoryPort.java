package com.taskflow.model.port;

import com.taskflow.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryPort {

    Project save(Project project);

    Optional<Project> findById(Long id);

    List<Project> findAll();

    void deleteById(Long id);
}
