package com.company;

import com.company.dao.DAO;
import com.company.model.Comment;
import com.company.model.Review;
import com.company.model.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Properties props = new Properties();
        Logger lgr = Logger.getLogger(Main.class.getName());

        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/db.properties");
            props.load(in);
            in.close();
        } catch (IOException ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return;
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        try {
            DAO dao = new DAO(url, username, password);
            dao.connect();

            System.out.println("Users: ");
            List<User> users = dao.getUserList();
            for (User user:users) {
                System.out.println(user.getName());
            }

            System.out.println("Comments: ");
            List<Comment> comments = dao.getCommentList();
            for (Comment comment:comments) {
                System.out.println(comment.getContent());
            }

            System.out.println("Reviews: ");
            List<Review> reviews = dao.getReviewList();
            for (Review review:reviews) {
                System.out.println(review.getGrade());
            }
        } catch (SQLException ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
