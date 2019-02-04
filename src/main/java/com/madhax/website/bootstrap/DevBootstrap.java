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
    }
}
