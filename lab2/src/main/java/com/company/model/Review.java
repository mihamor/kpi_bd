package com.company.model;

public class Review extends  Comment {

    private Grade grade;

    public Review(Long id, Long userId) {
        super(id, userId);
    }

    public Review(Long id, Long userId, String content, Grade grade) {
        super(id, userId, content);
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
