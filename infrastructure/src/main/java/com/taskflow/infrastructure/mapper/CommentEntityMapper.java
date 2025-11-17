package com.taskflow.infrastructure.mapper;

import com.taskflow.core.domain.Comment;
import com.taskflow.infrastructure.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    CommentEntity toEntity(Comment domain);

    Comment toDomain(CommentEntity entity);
}
