package com.springboot.services.interfaces;

import com.springboot.domain.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String userName);
    void addUser(String username, String password, String auth);
    List<User> listUsers();
}
