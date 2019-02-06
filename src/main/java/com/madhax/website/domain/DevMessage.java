package com.madhax.website.domain;

import javax.persistence.*;

@Entity
public class DevMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @ManyToOne
    private Project project;

    public DevMessage() { }

    public DevMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public DevMessage(String title, String message, Project project) {
        this.title = title;
        this.message = message;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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