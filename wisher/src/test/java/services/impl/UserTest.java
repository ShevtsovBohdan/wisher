package services.impl;


import com.springboot.configuration.JpaConfiguration;
import com.springboot.domain.User;

import com.springboot.repositories.UserRepository;
import com.springboot.services.impl.UserDaoImpl;
import com.springboot.services.interfaces.UserDao;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfiguration.class)
@Transactional
public class UserTest{

    private UserDao userService = new UserDaoImpl();

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindByUserName(){
        User users = userService.findByUserName("user");

        Assert.assertEquals(users.getPassword(), "pass");

    }

    @Test
    public void testListUsers(){
        List<User> users = userRepository.findAll();

        Assert.assertEquals(users.size(), 1);

    }
}
