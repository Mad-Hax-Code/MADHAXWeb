package com.madhax.website.service;

import com.madhax.website.domain.Project;
import com.madhax.website.exceptions.NotFoundException;
import com.madhax.website.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Set<Project> getAll() {
        Set<Project> projects = new HashSet<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    public Project getById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (!optionalProject.isPresent()) {
            throw new NotFoundException("Project not found for ID value: " + id);
        }

        return optionalProject.get();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Iterable<Project> saveAll(@Valid Iterable<Project> projects) {
        return projectRepository.saveAll(projects);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
