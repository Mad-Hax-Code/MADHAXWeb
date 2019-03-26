package com.madhax.website.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Feature extends BaseEntity {

    @Size(min = 3, max = 255)
    private String title;

    @Size(min = 3)
    @Lob
    private String body;

    private LocalDate date;

    @ManyToOne
    private Project project;

    public Feature() {
        this.date = LocalDate.now();
    }

    public Feature(String title, String body) {
        this();
        this.title = title;
        this.body = body;
    }

    public Feature(String title, String body, Project project) {
        this(title, body);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
