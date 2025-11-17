package com.taskflow.model;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private Long projectId;
    private Long userId;
    private LocalDateTime createdAt;

    // getters, setters, constructors
}
