package com.madhax.website.controller;

import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Project;
import com.madhax.website.service.FeatureService;
import com.madhax.website.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{projectId}/feature")
public class FeatureController {

    private final Logger log = LoggerFactory.getLogger(FeatureController.class);

    protected final String FEATURE_FORM_URL = "project/feature/featureForm";
    protected final String CONFIRM_DELETE_URL = "project/feature/confirmDelete";

    private final ProjectService projectService;
    private final FeatureService featureService;

    public FeatureController(ProjectService projectService, FeatureService featureService) {
        this.projectService = projectService;
        this.featureService = featureService;
    }

    @GetMapping("/new")
    public String newFeature(Model model, @PathVariable Long projectId) {
        model.addAttribute("project", projectService.getById(projectId));
        model.addAttribute("feature", new Feature());
        return FEATURE_FORM_URL;
    }

    @PostMapping("/new")
    public String saveNewFeature(@ModelAttribute Feature feature, @PathVariable Long projectId) {
        Project project = projectService.getById(projectId);
        project.addFeature(feature);
        projectService.save(project);
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/{featureId}/edit")
    public String editFeature(@PathVariable Long featureId, Model model) {
        model.addAttribute("feature", featureService.getById(featureId));
        return FEATURE_FORM_URL;
    }

    @PostMapping("/edit")
    public String saveEditedFeature(@ModelAttribute Feature feature, @PathVariable Long projectId) {
        log.info("Updating feature ID: {} for project: {}", feature.getId(), projectId);
        Feature savedFeature = featureService.save(feature);
        return "redirect:/project/" + savedFeature.getProject().getId();
    }

    @GetMapping("/{featureId}/delete")
    public String confirmDeleteById(@PathVariable Long featureId, Model model) {
        log.info("Delete confirmation for feature ID: {}", featureId);
        model.addAttribute("feature", featureService.getById(featureId));
        return CONFIRM_DELETE_URL;
    }

    @PostMapping("/delete")
    public String handleDeleteFeatureById(@PathVariable Long projectId, @RequestParam Long featureId) {
        log.info("Deleting feature id: {}", featureId);
        featureService.deleteById(featureId);
        return "redirect:/project/" + projectId;
    }
}
