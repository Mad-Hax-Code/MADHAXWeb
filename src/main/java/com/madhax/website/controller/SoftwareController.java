package com.madhax.website.controller;

import com.madhax.website.domain.Bug;
import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Log;
import com.madhax.website.domain.Project;
import com.madhax.website.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class SoftwareController {

    private ProjectRepository projectRepository;

    @Autowired
    public SoftwareController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/software")
    public String software(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "software/softwareList";
    }

    @GetMapping("/software/{id}")
    public String project(Model model, @PathVariable Long id) {

        Project project = projectRepository.findById(id).get();
        Set<Feature> features = project.getFeatures();
        Set<Log> devLog = project.getDevLog();
        Set<Bug> bugs = project.getBugs();

        model.addAttribute("project", project);
        model.addAttribute("features", features);
        model.addAttribute("devLog", devLog);

        return "software/project";
    }
}
