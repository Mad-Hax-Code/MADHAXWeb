package com.madhax.website.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Issue extends BaseEntity {

    @Size(min = 3, max = 255)
    private String title;

    @Size(min = 3)
    @Lob
    private String message;

    private LocalDate date = LocalDate.now();

    @Enumerated(value = EnumType.STRING)
    private IssueType issueType;

    @ManyToOne
    private Project project;

    public Issue() {
        this.date = LocalDate.now();
    }

    public Issue(String title, String message, IssueType issueType) {
        this();
        this.title = title;
        this.message = message;
        this.issueType = issueType;
    }

    public Issue(String title, String message, IssueType issueType, Project project) {
        this(title, message, issueType);
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

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
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