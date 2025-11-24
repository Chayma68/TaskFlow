package com.taskflow.service;

import com.taskflow.model.Priority;
import com.taskflow.model.Task;
import com.taskflow.model.TaskStatus;
import com.taskflow.model.port.TaskRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    /**
     * Crée une nouvelle tâche en appliquant des règles métier :
     * - titre obligatoire
     * - projet obligatoire (une tâche appartient toujours à un projet)
     * - priorité par défaut = MEDIUM si non renseignée
     * - statut par défaut = TODO si non renseigné
     * - date de création fixée à maintenant si absente
     */
    public Task createTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("La tâche ne peut pas être null");
        }

        // 🔹 Règle 1 : titre obligatoire
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Le titre de la tâche est obligatoire");
        }

        // 🔹 Règle 2 : la tâche doit appartenir à un projet
        if (task.getProjectId() == null) {
            throw new IllegalArgumentException("Une tâche doit être associée à un projet");
        }

        // 🔹 Règle 3 : priorité par défaut
        if (task.getPriority() == null) {
            task.setPriority(Priority.MEDIUM);
        }

        // 🔹 Règle 4 : statut par défaut
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }

        // 🔹 Règle 5 : date de création
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDateTime.now());
        }

        return taskRepositoryPort.save(task);
    }

    /**
     * Retourne une tâche par son id.
     * - id non null
     */
    public Optional<Task> getTaskById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'identifiant de la tâche ne peut pas être null");
        }
        return taskRepositoryPort.findById(id);
    }

    /**
     * Retourne toutes les tâches.
     */
    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }

    /**
     * Supprime une tâche avec des règles métier :
     * - la tâche doit exister
     * - on ne peut pas supprimer une tâche déjà terminée (DONE)
     */
    public void deleteTask(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'identifiant de la tâche ne peut pas être null");
        }

        Optional<Task> existingOpt = taskRepositoryPort.findById(id);

        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("La tâche avec l'id " + id + " n'existe pas");
        }

        Task existing = existingOpt.get();

        // 🔹 Règle métier : on ne supprime pas une tâche terminée
        if (existing.getStatus() == TaskStatus.DONE) {
            throw new IllegalStateException("Impossible de supprimer une tâche déjà terminée");
        }

        taskRepositoryPort.deleteById(id);
    }
}
