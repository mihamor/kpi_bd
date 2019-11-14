package com.company.model;

@TableName(name = "comments")
public class Comment {
    final protected Long userId;
    final protected Long id;
    protected String content;

    public Comment(Long id, Long userId) {
        this.userId = userId;
        this.id = id;
    }

    public Comment(Long id, Long userId, String content) {
        this.userId = userId;
        this.id = id;
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
