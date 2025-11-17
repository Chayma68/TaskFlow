package com.taskflow.infrastructure.adapter;

import com.taskflow.infrastructure.entity.ProjectEntity;
import com.taskflow.infrastructure.mapper.ProjectEntityMapper;
import com.taskflow.infrastructure.repository.ProjectRepository;
import com.taskflow.model.Project;
import com.taskflow.model.port.ProjectRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectRepositoryAdapter implements ProjectRepositoryPort {

    private final ProjectRepository projectRepository;
    private final ProjectEntityMapper projectEntityMapper;

    public ProjectRepositoryAdapter(ProjectRepository projectRepository,
                                    ProjectEntityMapper projectEntityMapper) {
        this.projectRepository = projectRepository;
        this.projectEntityMapper = projectEntityMapper;
    }

    @Override
    public Project save(Project project) {
        ProjectEntity entity = projectEntityMapper.toEntity(project);
        ProjectEntity saved = projectRepository.save(entity);
        return projectEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id)
                .map(projectEntityMapper::toDomain);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(projectEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
