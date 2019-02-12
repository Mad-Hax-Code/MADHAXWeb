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
    private String repositoryURL;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Feature> features = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<DevNote> devNotes = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Issue> issues = new HashSet<>();

    public Project() { }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    public String getRepositoryURL() {
        return repositoryURL;
    }

    public void setRepositoryURL(String repositoryURL) {
        this.repositoryURL = repositoryURL;
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

    public Set<DevNote> getDevNotes() {
        return devNotes;
    }

    public void setDevNotes(Set<DevNote> devNotes) {
        this.devNotes = devNotes;
    }

    public Project addDevMessage(DevNote devNote) {
        devNote.setProject(this);
        this.devNotes.add(devNote);
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
