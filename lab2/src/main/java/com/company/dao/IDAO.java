package com.company.dao;

import com.company.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDAO {

    User getUser(Long id);
    List<User> getUserList();
    User updateUser(User user);
    User deleteUser(Long id);
    User insertUser(User user);

    Rating getRating(Long id);
    List<Rating> getRatingList();
    Rating updateRating(Rating rating);
    Rating deleteRating(Long id);
    Rating insertRating(Rating rating);


    Answer getAnswer(Long id);
    List<Answer> getAnswerList();
    Answer updateAnswer(Answer answer);
    Answer deleteAnswer(Long id);
    Answer insertAnswer(Answer answer);


    Question getQuestion(Long id);
    List<Question> getQuestionList();
    Question updateQuestion(Question question);
    Question deleteQuestion(Long id);
    Question insertQuestion(Question question);


    Tag getTag(Long id);
    List<Tag> getTagList();
    Tag updateTag(Tag tag);
    Tag deleteTag(Long id);
    Tag insertTag(Tag tag);

    List<Question> searchWord(String word, boolean including) throws SQLException;
    ResultSet joinedQuestionSearch(Boolean disabled, String description) throws SQLException;

    String getEntityErrorMessage();
}
