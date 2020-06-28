package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;

public interface IUserDao {
    User getOneByLogin(String login);
    User getOneByEmail(String email);
    List<User> getAll();
    User save(User user);
    User remove(User user);
}

