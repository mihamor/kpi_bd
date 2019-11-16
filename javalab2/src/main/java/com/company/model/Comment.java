package com.company.model;

@TableName(name = "feedback")
@DiscriminatorValue("comment")
public class Comment extends Feedback {
    protected String content;

    public Comment () { super();}
    public Comment(Long id, Long userId) { super(id, userId);}
    public Comment(Long id, Long userId, String content) {
        super(id, userId);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
