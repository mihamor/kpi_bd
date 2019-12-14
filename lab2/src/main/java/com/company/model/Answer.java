package com.company.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    Long id;

    @Column(name = "creation_date")
    Timestamp creationDate;

    @Column(name = "uid", insertable = false, updatable = false)
    Long userId;

    @Column(name = "qid", insertable = false, updatable = false)
    Long questionId;

    @Column(name = "answer_text")
    String answerText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qid")
    private Question question;


    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    public Answer() {}

    public Answer(Long id, Timestamp creationDate, Long userId, Long questionId, String answerText) {
        this.id = id;
        this.creationDate = creationDate;
        this.userId = userId;
        this.questionId = questionId;
        this.answerText = answerText;
        this.ratings = new ArrayList<>();
    }

    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

    public List<Rating> getRatings() { return ratings; }

    public Question getQuestion() { return question; }

    public void setQuestion(Question question) { this.question = question; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setCreationDate(Timestamp creationDate) {
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
