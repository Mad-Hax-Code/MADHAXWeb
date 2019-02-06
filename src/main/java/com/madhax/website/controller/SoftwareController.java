package com.madhax.website.controller;

import com.madhax.website.domain.Project;
import com.madhax.website.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/software")
public class SoftwareController {

    private ProjectRepository projectRepository;

    @Autowired
    public SoftwareController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/")
    public String software(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "software/softwareList";
    }

    @GetMapping("/{id}")
    public String project(Model model, @PathVariable Long id) {

        Project project = projectRepository.findById(id).get();
        model.addAttribute("project", project);

        return "software/project";
    }

    @GetMapping("/add")
    public String addProject() {
        return "software/addProject";
    }

    @GetMapping("/manage")
    public String manage() {
        return "software/manageProjects";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.delete(projectRepository.findById(id).get());
        return "software/manageProjects";
    }
}
