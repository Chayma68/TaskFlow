package com.taskflow.model.port;

import com.taskflow.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryPort {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);

    List<Comment> findByTaskId(Long taskId);

    void deleteById(Long id);

    List<Comment> findAll();
}
