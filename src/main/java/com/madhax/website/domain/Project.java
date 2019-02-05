package com.madhax.website.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String description;
    private String version;
    private String gitHubURL;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Feature> features = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<DevMessage> devMessages = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Issue> issues = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGitHubURL() {
        return gitHubURL;
    }

    public void setGitHubURL(String gitHubURL) {
        this.gitHubURL = gitHubURL;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Project addFeature(Feature feature) {
        feature.setProject(this);
        this.features.add(feature);
        return this;
    }

    public Set<DevMessage> getDevMessages() {
        return devMessages;
    }

    public void setDevMessages(Set<DevMessage> devMessages) {
        this.devMessages = devMessages;
    }

    public Project addDevMessage(DevMessage devMessage) {
        devMessage.setProject(this);
        this.devMessages.add(devMessage);
        return this;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Project addIssue(Issue issue) {
        issue.setProject(this);
        this.issues.add(issue);
        return this;
    }
}
