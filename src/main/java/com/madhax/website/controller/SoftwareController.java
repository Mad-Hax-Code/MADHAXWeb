package com.madhax.website.controller;

import com.madhax.website.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
