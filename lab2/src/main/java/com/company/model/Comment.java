package com.company.model;

@TableName(name = "feedback")
@DiscriminatorValue("comment")
public class Comment {
    protected Long userId;
    protected Long id;
    protected String content;

    public Comment () {}

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

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
