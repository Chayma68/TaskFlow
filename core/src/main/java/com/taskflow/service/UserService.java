package com.taskflow.service;

import com.taskflow.model.Role;
import com.taskflow.model.User;
import com.taskflow.model.Task;
import com.taskflow.model.port.TaskRepositoryPort;
import com.taskflow.model.port.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepositoryPort userRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort,
                       TaskRepositoryPort taskRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
    }

    /**
     * Crée un utilisateur avec des règles métier :
     * - user non null
     * - nom obligatoire
     * - email obligatoire + format simple
     * - rôle par défaut = MEMBER si null
     */
    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("L'utilisateur ne peut pas être null");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'utilisateur est obligatoire");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email de l'utilisateur est obligatoire");
        }

        // Vérification simple d'email
        if (!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("L'email fourni n'est pas valide");
        }

        if (user.getRole() == null) {
            user.setRole(Role.MEMBER);
        }

        return userRepositoryPort.save(user);
    }

    public Optional<User> getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'id utilisateur ne peut pas être null");
        }
        return userRepositoryPort.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

    /**
     * Supprime un utilisateur avec une règle métier :
     * - l'utilisateur doit exister
     * - il ne doit plus avoir de tâches assignées
     */
    public void deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'id utilisateur ne peut pas être null");
        }

        Optional<User> existingOpt = userRepositoryPort.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("L'utilisateur avec l'id " + id + " n'existe pas");
        }

        // Règle métier : on ne supprime pas un user qui a encore des tâches
        List<Task> assignedTasks = taskRepositoryPort.findByAssigneeId(id);
        if (!assignedTasks.isEmpty()) {
            throw new IllegalStateException(
                    "Impossible de supprimer un utilisateur qui a encore des tâches assignées"
            );
        }

        userRepositoryPort.deleteById(id);
    }
}
