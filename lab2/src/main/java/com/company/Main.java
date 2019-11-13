package com.company;

import com.company.dao.DAO;

public class Main {

    public static void main(String[] args) {
        DAO dao = new DAO();
        System.out.println(dao.getUserList());
    }
}
