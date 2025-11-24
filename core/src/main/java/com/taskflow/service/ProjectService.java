package com.taskflow.service;

import com.taskflow.model.Project;
import com.taskflow.model.Task;
import com.taskflow.model.port.ProjectRepositoryPort;
import com.taskflow.model.port.TaskRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProjectService {

    private final ProjectRepositoryPort projectRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;

    public ProjectService(ProjectRepositoryPort projectRepositoryPort,
                          TaskRepositoryPort taskRepositoryPort) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
    }

    /**
     * Crée un projet avec des règles métier :
     * - nom obligatoire
     * - description nettoyée (trim) si non null
     */
    public Project createProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Le projet ne peut pas être null");
        }

        if (project.getName() == null || project.getName().isBlank()) {
            throw new IllegalArgumentException("Le nom du projet est obligatoire");
        }

        if (project.getDescription() != null) {
            project.setDescription(project.getDescription().trim());
        }

        return projectRepositoryPort.save(project);
    }

    public Optional<Project> getProjectById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'id du projet ne peut pas être null");
        }
        return projectRepositoryPort.findById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepositoryPort.findAll();
    }

    /**
     * Supprime un projet avec une règle métier :
     * - le projet doit exister
     * - il ne doit plus contenir de tâches
     */
    public void deleteProject(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'id du projet ne peut pas être null");
        }

        Optional<Project> existingOpt = projectRepositoryPort.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Le projet avec l'id " + id + " n'existe pas");
        }

        // Règle métier : pas de suppression si des tâches existent
        List<Task> tasks = taskRepositoryPort.findByProjectId(id);
        if (!tasks.isEmpty()) {
            throw new IllegalStateException(
                    "Impossible de supprimer un projet qui contient encore des tâches"
            );
        }

        projectRepositoryPort.deleteById(id);
    }
}
