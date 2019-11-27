package com.company.dao;

import com.company.model.*;

import java.sql.*;

import java.util.List;

public class DAO implements IDAO {

    IDAOImpl<User> usersDAOImpl;
    IDAOImpl<Rating> ratingDAOImpl;
    IDAOImpl<Answer> answerDAOImpl;
    IDAOImpl<Question> questionDAOImpl;
    IDAOImpl<Tag> tagDAOImpl;

    Connection connection;
    String url;
    String user;
    String password;

    public DAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws SQLException{
        connection = DriverManager.getConnection(url, user, password);
        usersDAOImpl = new DAOImpl<>(User.class, connection);
        ratingDAOImpl = new DAOImpl<>(Rating.class, connection);
        answerDAOImpl = new DAOImpl<>(Answer.class, connection);
        questionDAOImpl = new DAOImpl<>(Question.class, connection);
        tagDAOImpl = new DAOImpl<>(Tag.class, connection);
    }

    public User getUser(Long id) throws SQLException {
        return usersDAOImpl.getEntity(id);
    }

    public User updateUser(User user) throws SQLException, IllegalAccessException {
        return usersDAOImpl.updateEntity(user);
    }

    public Rating updateRating(Rating rating) throws SQLException, IllegalAccessException {
        return ratingDAOImpl.updateEntity(rating);
    }

    public Answer updateAnswer(Answer answer) throws SQLException, IllegalAccessException {
        return answerDAOImpl.updateEntity(answer);
    }

    public List<User> getUserList() throws SQLException {
        return usersDAOImpl.getEntityList();
    }
    public Rating getRating(Long id) throws SQLException {
        return ratingDAOImpl.getEntity(id);
    }
    public List<Rating> getRatingList() throws SQLException {
        return ratingDAOImpl.getEntityList();
    }

    public Answer getAnswer(Long id) throws SQLException {
        return answerDAOImpl.getEntity(id);
    }

    public List<Answer> getAnswerList() throws SQLException {
        return answerDAOImpl.getEntityList();
    }

    public Question getQuestion(Long id) throws SQLException {
        return questionDAOImpl.getEntity(id);
    }

    public List<Question> getQuestionList() throws SQLException {
        return questionDAOImpl.getEntityList();
    }

    public Question updateQuestion(Question question) throws SQLException, IllegalAccessException {
        return questionDAOImpl.updateEntity(question);
    }

    public Tag getTag(Long id) throws SQLException {
        return tagDAOImpl.getEntity(id);
    }

    public List<Tag> getTagList() throws SQLException {
        return tagDAOImpl.getEntityList();
    }

    public Tag updateTag(Tag tag) throws SQLException, IllegalAccessException {
        return tagDAOImpl.updateEntity(tag);
    }

    public String getEntityErrorMessage() {
        return usersDAOImpl.getEntityErrorMessage() + ratingDAOImpl.getEntityErrorMessage();
    }
}
