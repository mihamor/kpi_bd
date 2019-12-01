package com.company.model;

import java.text.SimpleDateFormat;

@Table(name = "answers")
public class Answer {

    @Primary
    @Column(name = "aid")
    Long id;

    @Column(name = "creation_date")
    SimpleDateFormat creationDate;

    @Column(name = "uid")
    Long userId;

    @Column(name = "qid")
    Long questionId;

    @Column(name = "answer_text")
    String answerText;

    public Answer() {}

    public Answer(Long id, SimpleDateFormat creationDate, Long userId, Long questionId, String answerText) {
        this.id = id;
        this.creationDate = creationDate;
        this.userId = userId;
        this.questionId = questionId;
        this.answerText = answerText;
    }

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public SimpleDateFormat getCreationDate() {
        return creationDate;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setCreationDate(SimpleDateFormat creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
