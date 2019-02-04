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
    @OneToMany
    private Set<Feature> features = new HashSet<>();
    @OneToMany
    private Set<Log> devLog = new HashSet<>();
    @OneToMany
    private Set<Bug> bugs = new HashSet<>();

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

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Set<Log> getDevLog() {
        return devLog;
    }

    public void setDevLog(Set<Log> devLog) {
        this.devLog = devLog;
    }

    public Set<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(Set<Bug> bugs) {
        this.bugs = bugs;
    }
}
