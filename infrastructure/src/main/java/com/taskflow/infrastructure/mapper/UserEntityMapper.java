package com.taskflow.infrastructure.mapper;


import com.taskflow.infrastructure.entity.UserEntity;
import com.taskflow.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);
}
