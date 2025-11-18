package com.taskflow.infrastructure.mapper;
import com.taskflow.infrastructure.entity.TaskEntity;
import com.taskflow.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskEntityMapper {

    TaskEntity toEntity(Task domain);

    Task toDomain(TaskEntity entity);
}
