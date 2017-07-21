package com.springboot.interfaces;

import com.springboot.domain.User;

import java.util.List;

/**
 * ManageUser is a base interface class for database connection and working with User object
 */
public interface ManageUser {
    public User findbyUserName(String uName);

    public Integer addUser(String username, String password, String auth);

    public List<User> listUsers();

}
