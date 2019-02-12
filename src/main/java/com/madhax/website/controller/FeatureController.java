package com.madhax.website.controller;

import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Project;
import com.madhax.website.repository.FeatureRepository;
import com.madhax.website.repository.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/software/features")
public class FeatureController {

    private final FeatureRepository featureRepository;
    private final ProjectRepository projectRepository;

    public FeatureController(FeatureRepository featureRepository, ProjectRepository projectRepository) {
        this.featureRepository = featureRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/new/{projectId}")
    public String newFeature(Model model, @PathVariable Long projectId) {
        model.addAttribute("project", projectRepository.findById(projectId).get());
        return "/software/feature/addFeature";
    }

    @PostMapping("/save/{projectId}")
    public String saveFeature(@ModelAttribute Feature feature, @PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        project.addFeature(feature);
        projectRepository.save(project);
        return "redirect:/software/" + project.getId();
    }

    @GetMapping("/edit/{featureId}")
    public String editFeature(@PathVariable Long featureId, Model model) {
        model.addAttribute("feature", featureRepository.findById(featureId).get());
        return "/software/feature/editFeature";
    }
}
