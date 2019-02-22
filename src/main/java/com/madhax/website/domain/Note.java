package com.madhax.website.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Note extends BaseEntity {

    private String title;
    @Lob
    private String message;
    @ManyToOne
    private Project project;

    public Note() { }

    public Note(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public Note(String title, String message, Project project) {
        this.title = title;
        this.message = message;
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
