package com.taskflow.infrastructure.mapper;

import com.taskflow.*;
import com.taskflow.infrastructure.entity.CommentEntity;
import com.taskflow.model.Comment;


@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    CommentEntity toEntity(Comment domain);

    Comment toDomain(CommentEntity entity);
}
