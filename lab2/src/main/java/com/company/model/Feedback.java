package com.company.model;

@DiscriminationColumn(name = "dtype")
public abstract class Feedback {
    protected Long userId;
    protected Long id;

    public Feedback () {}

    public Feedback(Long id, Long userId) {
        this.userId = userId;
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }
}
