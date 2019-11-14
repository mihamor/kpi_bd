package com.company.model;

@TableName(name = "feedback")
@DiscriminatorValue("review")
public class Review extends Feedback {

    private Grade grade;

    public Review () { super(); }

    public Review(Long id, Long userId) {
        super(id, userId);
    }

    public Review(Long id, Long userId, Grade grade) {
        super(id, userId);
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
