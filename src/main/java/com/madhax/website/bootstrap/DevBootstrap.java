package com.madhax.website.bootstrap;

import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Log;
import com.madhax.website.domain.Project;
import com.madhax.website.repository.BugRepository;
import com.madhax.website.repository.FeatureRepository;
import com.madhax.website.repository.LogRepository;
import com.madhax.website.repository.ProjectRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProjectRepository projectRepository;
    private final FeatureRepository featureRepository;
    private final LogRepository logRepository;
    private final BugRepository bugRepository;

    public DevBootstrap(ProjectRepository projectRepository, FeatureRepository featureRepository, LogRepository logRepository, BugRepository bugRepository) {
        this.projectRepository = projectRepository;
        this.featureRepository = featureRepository;
        this.logRepository = logRepository;
        this.bugRepository = bugRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextResfreshedEvent) {
        initData();
    }

    private void initData() {

        Project project1 = new Project();
        project1.setName("MADHAX Website");
        project1.setDescription("A website for software projects.");
        project1.setVersion("In development");
        project1.setGitHubURL("https://github.com/Mad-Hax-Code/MADHAXWeb");

        Feature project1Feature1 = new Feature();
        project1Feature1.setTitle("Dynamically Add Projects");
        project1Feature1.setBody("The application allows you to add and manage multiple projects.");
        Feature savedFeature1 = featureRepository.save(project1Feature1);

        Feature project1Feature2 = new Feature();
        project1Feature2.setTitle("List Features");
        project1Feature2.setBody("Add a list of features so that users are aware of the capabilities of the application");
        Feature savedFeature2 = featureRepository.save(project1Feature2);

        Feature project1Feature3 = new Feature();
        project1Feature3.setTitle("Keep The Community Informed");
        project1Feature3.setBody("Use the log feature to keep a history of development and upcoming improvements");
        Feature savedFeature3 = featureRepository.save(project1Feature3);

        Log project1Log1 = new Log();
        project1Log1.setTitle("In Development");
        project1Log1.setMessage("This project is currently in development. No releases have been published as of yet.");
        Log savedLog1 = logRepository.save(project1Log1);

        project1.getFeatures().add(savedFeature1);
        project1.getFeatures().add(savedFeature2);
        project1.getFeatures().add(savedFeature3);

        project1.getDevLog().add(savedLog1);

        projectRepository.save(project1);

        Project project2 = new Project();
        project2.setName("MADPorts");
        project2.setDescription("A mutli-threaded tcp scanning desktop application.");
        project2.setVersion("In development");
        project2.setGitHubURL("https://github.com/Mad-Hax-Code/MADPortsCLI");

        Feature project2Feature1 = new Feature();
        project2Feature1.setTitle("Scan Port Ranges");
        project2Feature1.setBody("The application scans the tcp ports for a given range.");
        Feature savedProject2Feature1 = featureRepository.save(project2Feature1);

        Feature project2Feature2 = new Feature();
        project2Feature2.setTitle("Multi-threaded Scans");
        project2Feature2.setBody("Divide the port range between a given number of threads to increase the speed.");
        Feature savedProject2Feature2 = featureRepository.save(project2Feature2);

        Feature project2Feature3 = new Feature();
        project2Feature3.setTitle("Choose Timeout Value");
        project2Feature3.setBody("Set the timeout value for each port in order to customize your scan.");
        Feature savedProject2Feature3 = featureRepository.save(project2Feature3);

        Log project2Log1 = new Log();
        project2Log1.setTitle("In Development");
        project2Log1.setMessage("This project is currently in development. No releases have been published as of yet.");
        Log savedProject2Log1 = logRepository.save(project2Log1);

        project2.getFeatures().add(savedProject2Feature1);
        project2.getFeatures().add(savedProject2Feature2);
        project2.getFeatures().add(savedProject2Feature3);

        project2.getDevLog().add(savedProject2Log1);

        projectRepository.save(project2);
    }
}
