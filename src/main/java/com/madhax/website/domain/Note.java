package com.madhax.website.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Note extends BaseEntity {

    @Size(min = 3, max = 255)
    private String title;

    @Size(min = 3)
    @Lob
    private String message;

    private LocalDate date;

    @ManyToOne
    private Project project;

    public Note() {
        this.date = LocalDate.now();
    }

    public Note(String title, String message) {
        this();
        this.title = title;
        this.message = message;
    }

    public Note(String title, String message, Project project) {
        this(title, message);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
