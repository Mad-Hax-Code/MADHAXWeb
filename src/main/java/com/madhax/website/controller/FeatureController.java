package com.madhax.website.controller;

import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Project;
import com.madhax.website.service.FeatureService;
import com.madhax.website.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/software/features")
public class FeatureController {

    private final ProjectService projectService;
    private final FeatureService featureService;

    public FeatureController(ProjectService projectService, FeatureService featureService) {
        this.projectService = projectService;
        this.featureService = featureService;
    }

    @GetMapping("/new/{projectId}")
    public String newFeature(Model model, @PathVariable Long projectId) {
        model.addAttribute("project", projectService.getById(projectId));
        return "/software/feature/addFeature";
    }

    @GetMapping("/edit/{featureId}")
    public String editFeature(@PathVariable Long featureId, Model model) {
        model.addAttribute("feature", featureService.getById(featureId));
        return "/software/feature/editFeature";
    }

    @PostMapping("/save/{projectId}")
    public String saveFeature(@ModelAttribute Feature feature, @PathVariable Long projectId) {
        Project project = projectService.getById(projectId);
        project.addFeature(feature);
        projectService.save(project);
        return "redirect:/software/" + project.getId();
    }
}
