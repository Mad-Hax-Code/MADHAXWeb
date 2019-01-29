package com.madhax.website.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Type(type = "text")
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Article() { }

    public Article(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
