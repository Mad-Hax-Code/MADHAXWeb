package com.madhax.website.controller;

import com.madhax.website.domain.Project;
import com.madhax.website.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/software")
public class SoftwareController {

    private ProjectService projectService;

    @Autowired
    public SoftwareController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping({"", "/"})
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAll());
        return "software/softwareList";
    }

    @GetMapping("/{id}")
    public String showProject(Model model, @PathVariable Long id) {

        Project project = projectService.getById(id);
        model.addAttribute("project", project);

        return "software/project";
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        return "software/newProject";
    }

    @GetMapping("/edit/{id}")
    public String editProject(Model model, @PathVariable Long id) {
        model.addAttribute("project", projectService.getById(id));
        return "software/editProject";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/software/" + project.getId();
    }

    @GetMapping("/delete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "software/confirmDelete";
    }

    @PostMapping("/delete")
    public String deleteProject(@ModelAttribute Project project) {
        projectService.delete(project);
        return "redirect:/software/softwareList";
    }
}
