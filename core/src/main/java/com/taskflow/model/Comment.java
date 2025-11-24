package com.taskflow.model;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private Long taskId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;

    // ----- Constructors -----

    public Comment() {}

    public Comment(Long id, Long taskId, Long userId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    // ----- Getters -----

    public Long getId() {
        return id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ----- Setters -----

    public void setId(Long id) {
        this.id = id;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
