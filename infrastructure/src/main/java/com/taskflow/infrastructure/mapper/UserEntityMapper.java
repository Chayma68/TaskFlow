package com.taskflow.infrastructure.mapper;

import com.taskflow.core.domain.User;
import com.taskflow.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);
}
