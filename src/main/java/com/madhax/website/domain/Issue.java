package com.madhax.website.domain;

import javax.persistence.*;

@Entity
public class Issue extends BaseEntity {

    private String title;
    @Lob
    private String body;

    @Enumerated(value = EnumType.STRING)
    private IssueType issueType;

    @ManyToOne
    private Project project;

    public Issue() { }

    public Issue(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Issue(String title, String body, IssueType issueType, Project project) {
        this.title = title;
        this.body = body;
        this.issueType = issueType;
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