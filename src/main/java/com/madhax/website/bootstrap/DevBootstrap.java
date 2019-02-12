package com.madhax.website.bootstrap;

import com.madhax.website.domain.DevNote;
import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Issue;
import com.madhax.website.domain.Project;
import com.madhax.website.service.ProjectService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProjectService projectService;

    public DevBootstrap(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        projectService.saveAll(initData());
    }

    private List<Project> initData() {

        List<Project> projects = new ArrayList<>();

        Project project1 = new Project();
        project1.setName("MADHAX Website");
        project1.setDescription("A website for software projects.");
        project1.setVersion("In development");
        project1.setRepositoryURL("https://github.com/Mad-Hax-Code/MADHAXWeb");

        project1.addFeature(new Feature(
                "Dynamically Add Projects",
                "The application allows you to add and manage multiple projects."
        ));

        project1.addFeature(new Feature(
                "List Application Features",
                "Add a list of features so that users are aware of the capabilities of the application"
        ));

        project1.addFeature(new Feature(
                "Keep The Community Informed",
                "Use the log feature to keep a history of development and upcoming improvements"
        ));

        project1.addDevMessage(new DevNote(
                "In Development",
                "This project is currently in development. No releases have been published as of yet."
        ));

        projects.add(project1);

        Project project2 = new Project();
        project2.setName("MADPorts");
        project2.setDescription("A mutli-threaded tcp scanning desktop application.");
        project2.setVersion("In development");
        project2.setRepositoryURL("https://github.com/Mad-Hax-Code/MADPortsCLI");

        project2.addFeature(new Feature(
                "Scan Port Ranges",
                "The application scans the tcp ports for a given range."
        ));

        project2.addFeature(new Feature(
                "Multi-threaded Scans",
                "Divide the port range between a given number of threads to increase the speed."
        ));

        project2.addFeature(new Feature(
                "Choose Timeout Value",
                "Set the timeout value for each port in order to customize your scan."
        ));

        project2.addDevMessage(new DevNote(
                "In Development",
                "This project is currently in development. No releases have been published as of yet."
        ));

        project2.addIssue(new Issue(
                "Wrong Port Count",
                "ScanResult port count is returning incorrect value."
        ));

        projects.add(project2);

        return projects;
    }
}
