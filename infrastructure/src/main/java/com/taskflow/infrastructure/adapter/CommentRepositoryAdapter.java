package com.taskflow.infrastructure.adapter;

import com.taskflow.infrastructure.entity.CommentEntity;
import com.taskflow.infrastructure.mapper.CommentEntityMapper;
import com.taskflow.infrastructure.repository.CommentRepository;
import com.taskflow.model.Comment;
import com.taskflow.model.port.CommentRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommentRepositoryAdapter implements CommentRepositoryPort {

    private final CommentRepository commentRepository;
    private final CommentEntityMapper commentEntityMapper;

    public CommentRepositoryAdapter(CommentRepository commentRepository,
                                    CommentEntityMapper commentEntityMapper) {
        this.commentRepository = commentRepository;
        this.commentEntityMapper = commentEntityMapper;
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity entity = commentEntityMapper.toEntity(comment);
        CommentEntity saved = commentRepository.save(entity);
        return commentEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id)
                .map(commentEntityMapper::toDomain);
    }

    @Override
    public List<Comment> findByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(commentEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

}
