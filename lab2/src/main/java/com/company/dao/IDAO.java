package com.company.dao;

import com.company.model.User;

import java.util.List;

public interface IDAO {
    User getUser(Long id);
    List<User> getUserList();


}
