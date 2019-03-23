package com.madhax.website.controller;

import com.madhax.website.domain.Project;
import com.madhax.website.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final Logger log = LoggerFactory.getLogger(ProjectController.class);

    protected final String PROJECT_FORM_URL = "project/projectForm";
    protected final String VIEW_PROJECT_URL = "project/viewProject";
    protected final String PROJECT_LIST_URL = "project/projectList";
    protected final String CONFIRM_DELETE_URL = "project/confirmDelete";

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping({"", "/"})
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAll());
        return PROJECT_LIST_URL;
    }

    @GetMapping("/{id}")
    public String showProject(Model model, @PathVariable Long id) {

        Project project = projectService.getById(id);
        model.addAttribute("project", project);

        return VIEW_PROJECT_URL;
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        return PROJECT_FORM_URL;
    }

    @GetMapping("/edit/{projectId}")
    public String editProject(Model model, @PathVariable Long projectId) {
        model.addAttribute("project", projectService.getById(projectId));
        return PROJECT_FORM_URL;
    }

    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute Project project, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));

            return PROJECT_FORM_URL;
        }
        Project savedProject = projectService.save(project);
        return "redirect:/project/" + savedProject.getId();
    }

    @GetMapping("/delete/{projectId}")
    public String confirmDelete(@PathVariable Long projectId, Model model) {
        model.addAttribute("project", projectService.getById(projectId));
        return CONFIRM_DELETE_URL;
    }

    @PostMapping("/delete")
    public String deleteProject(@RequestParam Long projectId) {
        log.debug("Deleting project with ID: {}", projectId);
        projectService.deleteById(projectId);
        return "redirect:/project";
    }
}
