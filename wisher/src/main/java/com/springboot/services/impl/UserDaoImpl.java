package com.springboot.services.impl;

import com.springboot.domain.User;
import com.springboot.repositories.UserRepository;
import com.springboot.services.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void addUser(String username, String password, String auth) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAuthorities(auth);
        userRepository.save(user);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
