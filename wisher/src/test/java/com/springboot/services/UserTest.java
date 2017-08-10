package com.springboot.services;


import com.springboot.configuration.JpaConfiguration;
import com.springboot.domain.User;
import com.springboot.repositories.UserRepository;
import com.springboot.services.interfaces.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfiguration.class)
@ComponentScan("com.springboot.com.springboot.services")
@Transactional
public class UserTest{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;


    @Test
    public void testFindByName(){
        User users = userDao.findByUsername("user123");
//        User users = userRepository.findByUsername("user123").get(0);

        Assert.assertEquals(users.getPassword(), "pass123");

    }

    @Test
    public void testListUsers(){
        List<User> users = userRepository.findAll();

        Assert.assertEquals(users.size(), 2);

    }

    @Test
    public void testAddUser(){
        userDao.addUser("user321", "pass321", "USER");

        List<User> userList = userDao.listUsers();
        Assert.assertEquals(userList.size(), 3);

    }
}
