package com.taskflow.service;

import com.taskflow.model.Project;
import com.taskflow.model.port.ProjectRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProjectService {

    private final ProjectRepositoryPort projectRepositoryPort;

    public ProjectService(ProjectRepositoryPort projectRepositoryPort) {
        this.projectRepositoryPort = projectRepositoryPort;
    }

    public Project createProject(Project project) {
        return projectRepositoryPort.save(project);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepositoryPort.findById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepositoryPort.findAll();
    }

    public void deleteProject(Long id) {
        projectRepositoryPort.deleteById(id);
    }
}
