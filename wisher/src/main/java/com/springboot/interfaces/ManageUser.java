package com.springboot.interfaces;

import com.springboot.domain.User;

import java.util.List;

public interface ManageUser {
    public User findbyUsername(String uName);
    public Integer addUser(String username, String password, String auth);
//    public void ListUser();
    public List<User> listUsers();
}
