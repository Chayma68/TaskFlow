package com.taskflow.service;

import com.taskflow.model.Comment;
import com.taskflow.model.port.CommentRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CommentService {

    private final CommentRepositoryPort commentRepositoryPort;

    public CommentService(CommentRepositoryPort commentRepositoryPort) {
        this.commentRepositoryPort = commentRepositoryPort;
    }

    public Comment createComment(Comment comment) {
        return commentRepositoryPort.save(comment);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepositoryPort.findById(id);
    }

    public List<Comment> getAllComments() {
        return commentRepositoryPort.findAll();
    }

    public void deleteComment(Long id) {
        commentRepositoryPort.deleteById(id);
    }
}
