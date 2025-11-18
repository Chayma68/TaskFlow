package com.taskflow.infrastructure.mapper;

import com.taskflow.infrastructure.entity.CommentEntity;
import com.taskflow.model.Comment;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    CommentEntity toEntity(Comment domain);

    Comment toDomain(CommentEntity entity);
}
