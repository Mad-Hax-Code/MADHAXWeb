package com.madhax.website.service;

import com.madhax.website.domain.Project;
import com.madhax.website.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectService {

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
        Project project;
        project = projectRepository.findById(id).orElse(null);
        return project;
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Iterable<Project> saveAll(Iterable<Project> projects) {
        return projectRepository.saveAll(projects);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
