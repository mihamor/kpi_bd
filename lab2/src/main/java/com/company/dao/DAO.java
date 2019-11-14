package com.company.dao;

import com.company.model.Comment;
import com.company.model.User;
import java.sql.*;

import java.util.List;

public class DAO implements IDAO {

    IDAOImpl<User> usersDAOImpl;
    IDAOImpl<Comment> commentDAOImpl;
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
        usersDAOImpl = new DAOImpl<User>(User.class, connection);
        commentDAOImpl = new DAOImpl<Comment>(Comment.class, connection);
    }

    public User getUser(Long id) throws SQLException {
        return usersDAOImpl.getEntity(id);
    }
    public List<User> getUserList() throws SQLException {
        return usersDAOImpl.getEntityList();
    }
    public Comment getComment(Long id) throws SQLException {
        return commentDAOImpl.getEntity(id);
    }
    public List<Comment> getCommentList() throws SQLException {
        return commentDAOImpl.getEntityList();
    }

    public String getEntityErrorMessage() {
        return usersDAOImpl.getEntityErrorMessage() + commentDAOImpl.getEntityErrorMessage();
    }
}
