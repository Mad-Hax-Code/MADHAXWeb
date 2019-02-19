package com.madhax.website.controller;

import com.madhax.website.domain.Project;
import com.madhax.website.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final Logger log = LoggerFactory.getLogger(ProjectController.class);
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping({"", "/"})
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAll());
        return "project/projectList";
    }

    @GetMapping("/{id}")
    public String showProject(Model model, @PathVariable Long id) {

        Project project = projectService.getById(id);
        model.addAttribute("project", project);

        return "project/viewProject";
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        return "project/newProject";
    }

    @GetMapping("/{projectId}/edit")
    public String editProject(Model model, @PathVariable Long projectId) {
        model.addAttribute("project", projectService.getById(projectId));
        return "project/editProject";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/{projectId}/delete")
    public String confirmDelete(@PathVariable Long projectId, Model model) {
        model.addAttribute("project", projectService.getById(projectId));
        return "project/confirmDelete";
    }

    @PostMapping("/delete")
    public String deleteProject(@RequestParam Long projectId) {
        log.debug("Deleting project with ID: {}", projectId);
        projectService.deleteById(projectId);
        return "redirect:/project";
    }
}
