package com.madhax.website.controller;

import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Project;
import com.madhax.website.service.FeatureService;
import com.madhax.website.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/project/feature")
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

    @GetMapping("/new/{projectId}")
    public String newFeature(Model model, @PathVariable Long projectId) {
        model.addAttribute("project", projectService.getById(projectId));
        model.addAttribute("feature", new Feature());
        return FEATURE_FORM_URL;
    }

    @PostMapping("/new/{projectId}")
    public String saveNewFeature(@Valid @ModelAttribute Feature feature, @PathVariable Long projectId, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return FEATURE_FORM_URL;
        }
        Project project = projectService.getById(projectId);
        project.addFeature(feature);
        projectService.save(project);
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/edit/{projectId}/{featureId}")
    public String editFeature(@PathVariable Long featureId, Model model) {
        model.addAttribute("feature", featureService.getById(featureId));
        return FEATURE_FORM_URL;
    }

    @PostMapping("/edit/{projectId}")
    public String saveEditedFeature(@Valid @ModelAttribute Feature feature, @PathVariable Long projectId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return FEATURE_FORM_URL;
        }
        log.info("Updating feature ID: {} for project: {}", feature.getId(), projectId);
        Feature savedFeature = featureService.save(feature);
        return "redirect:/project/" + savedFeature.getProject().getId();
    }

    @GetMapping("/delete/{projectId}/{featureId}")
    public String confirmDeleteById(@PathVariable Long featureId, Model model) {
        log.info("Delete confirmation for feature ID: {}", featureId);
        model.addAttribute("feature", featureService.getById(featureId));
        return CONFIRM_DELETE_URL;
    }

    @PostMapping("/delete/{projectId}")
    public String handleDeleteFeatureById(@PathVariable Long projectId, @RequestParam Long featureId) {
        log.info("Deleting feature id: {}", featureId);
        featureService.deleteById(featureId);
        return "redirect:/project/" + projectId;
    }
}
