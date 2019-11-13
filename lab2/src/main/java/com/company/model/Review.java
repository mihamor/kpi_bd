package com.company.model;

public class Review extends  Comment {

    private Grade grade;

    public Review(Long id, User user) {
        super(id, user);
    }

    public Review(Long id, User user, String content, Grade grade) {
        super(id, user, content);
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
