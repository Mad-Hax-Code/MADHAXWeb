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
        return FEATURE_FORM_URL;
    }

    @GetMapping("/edit/{featureId}")
    public String editFeature(@PathVariable Long featureId, Model model) {
        model.addAttribute("feature", featureService.getById(featureId));
        return FEATURE_FORM_URL;
    }

    @PostMapping("/save")
    public String saveFeature(@ModelAttribute Feature feature, @PathVariable Long projectId) {
        Project project = projectService.getById(projectId);
        project.addFeature(feature);
        projectService.save(project);
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/delete/{featureId}")
    public String confirmDeleteById(@PathVariable Long featureId, Model model) {
        log.debug("Delete confirmation for feature ID: {}", featureId);
        model.addAttribute("feature", featureService.getById(featureId));
        return CONFIRM_DELETE_URL;
    }

    @PostMapping("/delete")
    public String handleDeleteFeatureById(@PathVariable Long projectId, @RequestParam Long featureId) {
        log.debug("Delete feature, project id: {} feature id: {}", projectId, featureId);
        featureService.deleteById(featureId);
        return "redirect:/project/" + projectId;
    }
}
