package com.taskflow.infrastructure.mapper;

import com.taskflow.infrastructure.entity.ProjectEntity;
import com.taskflow.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectEntityMapper {

    ProjectEntity toEntity(Project domain);

    Project toDomain(ProjectEntity entity);
}
