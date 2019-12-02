package com.company;

import com.company.controller.Controller;
import com.company.dao.DAO;
import com.company.view.View;

import java.io.FileInputStream;
import java.io.IOException;
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
            View view = new View();
            DAO dao = new DAO(url, username, password);
            dao.connect();

            Controller controller = new Controller(view, dao);
            controller.mainMenu();


        } catch (Exception ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
