package com.company.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long id;

    private String username;
    private String fullname;
    private String passhash;
    private Boolean disabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    public User () {}
    public User(Long id, String username, String fullname, String passhash, Boolean disabled) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.passhash = passhash;
        this.disabled = disabled;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addAnswer(Answer answer) {
        answer.setUser(this);
        answers.add(answer);
    }

    public void addQuestion(Question question) {
        question.setUser(this);
        questions.add(question);
    }

    public  void addRating(Rating rating) {
        rating.setUser(this);
        ratings.add(rating);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }

    public void removeRating(Rating rating) {
        ratings.remove(rating);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPasshash() {
        return passhash;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }

    public Boolean getDisabled() { return disabled; }

    public void setDisabled(Boolean disabled) { this.disabled = disabled; }
}
