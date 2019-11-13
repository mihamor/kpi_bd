package com.company.model;

public class Comment {
    final protected User user;
    final protected Long id;
    protected String content;

    public Comment(Long id, User user) {
        this.user = user;
        this.id = id;
    }

    public Comment(Long id, User user, String content) {
        this.user = user;
        this.id = id;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
