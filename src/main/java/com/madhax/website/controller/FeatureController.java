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
@RequestMapping("/project/{projectId}/features")
public class FeatureController {

    private final Logger log = LoggerFactory.getLogger(FeatureController.class);

    private final ProjectService projectService;
    private final FeatureService featureService;

    public FeatureController(ProjectService projectService, FeatureService featureService) {
        this.projectService = projectService;
        this.featureService = featureService;
    }

    @GetMapping("/new")
    public String newFeature(Model model, @PathVariable Long projectId) {
        model.addAttribute("project", projectService.getById(projectId));
        return "/project/feature/addFeature";
    }

    @GetMapping("/edit/{featureId}")
    public String editFeature(@PathVariable Long featureId, Model model) {
        model.addAttribute("feature", featureService.getById(featureId));
        return "/project/feature/editFeature";
    }

    @PostMapping("/save")
    public String saveFeature(@ModelAttribute Feature feature, @PathVariable Long projectId) {
        Project project = projectService.getById(projectId);
        project.addFeature(feature);
        projectService.save(project);
        return "redirect:/project/" + project.getId();
    }

    @PostMapping("/delete")
    public String deleteFeatureById(@PathVariable Long projectId, @RequestParam Long featureId) {
        log.debug("Delete feature, project id: {} feature id: {}", projectId, featureId);
        featureService.deleteById(featureId);
        return "redirect:/project/" + projectId;
    }
}
