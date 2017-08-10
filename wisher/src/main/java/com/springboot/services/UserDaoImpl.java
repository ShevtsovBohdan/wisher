package com.springboot.services;

import com.springboot.configuration.JpaConfiguration;
import com.springboot.domain.User;
import com.springboot.repositories.UserRepository;
import com.springboot.services.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName).get(0);
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
