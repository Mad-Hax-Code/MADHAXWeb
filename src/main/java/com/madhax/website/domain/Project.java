package com.madhax.website.domain;

import org.hibernate.validator.constraints.URL;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 3)
    @Lob
    private String description;

    private String version;

    @URL
    private String repositoryURL;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Feature> features = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Note> notes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Issue> issues = new HashSet<>();

    public Project() { }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Project addNote(Note note) {
        note.setProject(this);
        this.notes.add(note);
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
