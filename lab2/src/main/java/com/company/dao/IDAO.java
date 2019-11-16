package com.company.dao;

import com.company.model.Comment;
import com.company.model.Review;
import com.company.model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO {

    void connect() throws SQLException;

    User getUser(Long id) throws SQLException;

    List<User> getUserList() throws SQLException;
    void updateUser(User user) throws SQLException, IllegalAccessException;

    Comment getComment(Long id) throws SQLException;

    List<Comment> getCommentList() throws SQLException;

    Review getReview(Long id) throws SQLException;

    List<Review> getReviewList() throws SQLException;

    String getEntityErrorMessage();
}
