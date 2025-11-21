package com.taskflow.service;

import com.taskflow.model.User;
import com.taskflow.model.port.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepositoryPort.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

    public void deleteUser(Long id) {
        userRepositoryPort.deleteById(id);
    }
}
