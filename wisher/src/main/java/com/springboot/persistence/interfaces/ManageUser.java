package com.springboot.persistence.interfaces;

import com.springboot.domain.User;

import java.util.List;

/**
 * ManageUser is a base interface class for database connection and working with User object
 */
public interface ManageUser {
    User findbyUserName(String uName);

    Integer addUser(String username, String password, String auth);

    List<User> listUsers();

}
