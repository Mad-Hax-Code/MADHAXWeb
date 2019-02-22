package com.madhax.website.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Feature extends BaseEntity {

    @Size(min=3, max=255)
    private String title;

    @NotBlank
    @Lob
    private String body;

    @ManyToOne
    private Project project;

    public Feature() { }

    public Feature(String title, String body) {
        this.title = title;
        this.body = body;
        this.project = project;
    }

    public Feature(String title, String body, Project project) {
        this.title = title;
        this.body = body;
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
