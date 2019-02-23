package com.madhax.website.domain;

import javax.persistence.*;

@Entity
public class Issue extends BaseEntity {

    private String title;
    @Lob
    private String message;

    @Enumerated(value = EnumType.STRING)
    private IssueType issueType;

    @ManyToOne
    private Project project;

    public Issue() { }

    public Issue(String title, String message, IssueType issueType) {
        this.title = title;
        this.message = message;
        this.issueType = issueType;
    }

    public Issue(String title, String message, IssueType issueType, Project project) {
        this.title = title;
        this.message = message;
        this.issueType = issueType;
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
}