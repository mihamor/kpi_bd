package com.company;

import com.company.dao.DAO;
import com.company.model.*;

import java.io.FileInputStream;
import java.io.IOException;
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
                System.out.println(user.getFullname());
                System.out.println(user.getPasshash());
            }

            System.out.println("Answers: ");
            List<Answer> answers = dao.getAnswerList();
            for (Answer answer:answers) {
                System.out.println(answer.getAnswerText());
            }

            System.out.println("Questions: ");
            List<Question> questions = dao.getQuestionList();
            for (Question question:questions) {
                System.out.println(question.getEssence());
            }

            System.out.println("Ratings: ");
            List<Rating> ratings = dao.getRatingList();
            for (Rating rating:ratings) {
                System.out.println(rating.getQuantity());
            }

            System.out.println("Tags: ");
            List<Tag> tags = dao.getTagList();
            for (Tag tag:tags) {
                System.out.println(tag.getName());
            }


            User user = new User(null, "username", "fullname", "passhash");
//            User deletedUser = dao.deleteUser((long) 3);
//            System.out.println("Deleted user: ");
//            System.out.println(deletedUser.getUsername());
//            User insertedUser = dao.insertUser(user);
//            System.out.println("Inserted user: " + insertedUser.getId());

            System.out.println("Question search: ");
            List<Question> foundedQuestions = dao.searchWord("help", true);
            for (Question question:foundedQuestions) {
                System.out.println(question.getEssence() + " " + question.getDescription());
            }

        } catch (Exception ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
