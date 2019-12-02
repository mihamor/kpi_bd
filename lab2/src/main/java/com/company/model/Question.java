package com.company.model;

import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "questions")
public class Question {

    @Primary
    @Column(name = "qid")
    private Long id;


    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "uid")
    private Long userId;

    private String essence;
    private String description;

    public Question () {}
    public Question(Long id, Long userId, Timestamp creationDate, String essence, String description) {
        this.id = id;
        this.userId = userId;
        this.creationDate = creationDate;
        this.essence = essence;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEssence() {
        return essence;
    }

    public void setEssence(String essence) {
        this.essence = essence;
    }
}
