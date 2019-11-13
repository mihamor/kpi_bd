package com.company.dao;

import com.company.model.Comment;
import com.company.model.User;

import java.util.List;

public class DAO implements IDAO {

    IDAOImpl<User> usersDAOImpl;
    IDAOImpl<Comment> commentDAOImpl;

    public User getUser(Long id) {
        return usersDAOImpl.getEntity(id);
    }

    public List<User> getUserList() {
        return usersDAOImpl.getEntityList();
    }

    public Comment getComment(Long id) {
        return commentDAOImpl.getEntity(id);
    }

    public List<Comment> getCommentList() {
        return commentDAOImpl.getEntityList();
    }
}
