package com.company.dao;

import com.company.model.*;

import java.sql.SQLException;
import java.util.List;

public interface IDAO {

    void connect() throws SQLException;

    User getUser(Long id) throws SQLException;
    List<User> getUserList() throws SQLException;
    User updateUser(User user) throws SQLException, IllegalAccessException;

    Rating getRating(Long id) throws SQLException;
    List<Rating> getRatingList() throws SQLException;
    Rating updateRating(Rating rating) throws SQLException, IllegalAccessException;

    Answer getAnswer(Long id) throws SQLException;
    List<Answer> getAnswerList() throws SQLException;
    Answer updateAnswer(Answer answer) throws SQLException, IllegalAccessException;

    Question getQuestion(Long id) throws SQLException;
    List<Question> getQuestionList() throws SQLException;
    Question updateQuestion(Question question) throws SQLException, IllegalAccessException;

    Tag getTag(Long id) throws SQLException;
    List<Tag> getTagList() throws SQLException;
    Tag updateTag(Tag tag) throws SQLException, IllegalAccessException;
    String getEntityErrorMessage();
}
