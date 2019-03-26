package com.madhax.website.controller;

import com.madhax.website.domain.Issue;
import com.madhax.website.domain.IssueType;
import com.madhax.website.domain.Project;
import com.madhax.website.service.IssueService;
import com.madhax.website.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by James Cathcart on 2/22/2019
 */
@Controller
@RequestMapping("project/issue")
public class IssueController {

    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    protected final String CONFIRM_DELETE_URL = "project/issue/confirmDelete";
    protected final String ISSUE_FORM_URL = "project/issue/issueForm";

    private final ProjectService projectService;
    private final IssueService issueService;

    public IssueController(ProjectService projectService, IssueService issueService) {
        this.projectService = projectService;
        this.issueService = issueService;
    }

    @GetMapping("/new/{projectId}")
    public String newIssue(@PathVariable Long projectId, Model model) {
        model.addAttribute("issue", new Issue());
        model.addAttribute("projectId", projectId);
        model.addAttribute("issueTypes", IssueType.values());
        return ISSUE_FORM_URL;
    }

    @PostMapping("/new/{projectId}")
    public String saveNewIssue(@PathVariable Long projectId, @Valid @ModelAttribute Issue issue, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));
            return ISSUE_FORM_URL;
        }
        Project project = projectService.getById(projectId);
        project.addIssue(issue);
        projectService.save(project);
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/edit/{projectId}/{issueId}")
    public String editIssue(@PathVariable Long issueId, Model model) {
        model.addAttribute("issue", issueService.getById(issueId));
        model.addAttribute("issueTypes", IssueType.values());
        return ISSUE_FORM_URL;
    }

    @PostMapping("/edit/{projectId}")
    public String saveEditedIssue(@Valid @ModelAttribute Issue issue, @PathVariable Long projectId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return ISSUE_FORM_URL;
        }
        log.debug("Updating issue ID: {} for project: {}", issue.getId(), projectId);
        Issue savedIssue = issueService.save(issue);
        return "redirect:/project/" + projectId;
    }

    @GetMapping("/delete/{projectId}/{issueId}")
    public String confirmDeleteById(@PathVariable Long issueId, Model model) {
        log.debug("Delete confirmation for issue ID: {}", issueId);
        model.addAttribute("issue", issueService.getById(issueId));
        return CONFIRM_DELETE_URL;
    }

    @PostMapping("/delete/{projectId}")
    public String handleDeleteIssueById(@PathVariable Long projectId, @RequestParam Long issueId) {
        issueService.deleteById(issueId);
        return "redirect:/project/" + projectId;
    }
}