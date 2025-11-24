package com.taskflow.service;

import com.taskflow.model.Comment;
import com.taskflow.model.Task;
import com.taskflow.model.User;
import com.taskflow.model.port.CommentRepositoryPort;
import com.taskflow.model.port.TaskRepositoryPort;
import com.taskflow.model.port.UserRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentService {

    private final CommentRepositoryPort commentRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public CommentService(CommentRepositoryPort commentRepositoryPort,
                          TaskRepositoryPort taskRepositoryPort,
                          UserRepositoryPort userRepositoryPort) {
        this.commentRepositoryPort = commentRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    /**
     * Crée un commentaire avec des règles métier :
     * - contenu obligatoire, non vide
     * - lié à une tâche existante
     * - lié à un utilisateur existant
     * - date de création = maintenant si null
     */
    public Comment createComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Le commentaire ne peut pas être null");
        }

        if (comment.getContent() == null || comment.getContent().isBlank()) {
            throw new IllegalArgumentException("Le contenu du commentaire est obligatoire");
        }

        // on peut limiter la taille par exemple à 1000 caractères
        if (comment.getContent().length() > 1000) {
            throw new IllegalArgumentException("Le commentaire est trop long (max 1000 caractères)");
        }

        if (comment.getTaskId() == null) {
            throw new IllegalArgumentException("Le commentaire doit être associé à une tâche");
        }

        if (comment.getUserId() == null) {
            throw new IllegalArgumentException("Le commentaire doit être associé à un utilisateur");
        }

        // Vérifier que la tâche existe
        Optional<Task> taskOpt = taskRepositoryPort.findById(comment.getTaskId());
        if (taskOpt.isEmpty()) {
            throw new IllegalArgumentException("La tâche associée au commentaire n'existe pas");
        }

        // Vérifier que l'utilisateur existe
        Optional<User> userOpt = userRepositoryPort.findById(comment.getUserId());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("L'utilisateur associé au commentaire n'existe pas");
        }

        if (comment.getCreatedAt() == null) {
            comment.setCreatedAt(LocalDateTime.now());
        }

        return commentRepositoryPort.save(comment);
    }

    public Optional<Comment> getCommentById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'id du commentaire ne peut pas être null");
        }
        return commentRepositoryPort.findById(id);
    }

    /**
     * Récupère les commentaires d'une tâche avec une validation.
     */
    public List<Comment> getCommentsByTaskId(Long taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("L'id de la tâche ne peut pas être null");
        }

        // Optionnel : vérifier que la tâche existe
        Optional<Task> taskOpt = taskRepositoryPort.findById(taskId);
        if (taskOpt.isEmpty()) {
            throw new IllegalArgumentException("La tâche avec l'id " + taskId + " n'existe pas");
        }

        return commentRepositoryPort.findByTaskId(taskId);
    }

    public List<Comment> getAllComments() {
        return commentRepositoryPort.findAll();
    }

    public void deleteComment(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'id du commentaire ne peut pas être null");
        }

        Optional<Comment> existingOpt = commentRepositoryPort.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Le commentaire avec l'id " + id + " n'existe pas");
        }

        commentRepositoryPort.deleteById(id);
    }
}
