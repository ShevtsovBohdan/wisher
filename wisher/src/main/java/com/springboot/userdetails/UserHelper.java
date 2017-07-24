package com.springboot.userdetails;


import org.springframework.security.core.userdetails.User;

/**
 * Class Wrapper to org.springframework.security.core.UserDetails.User;
 */
public class UserHelper{
    User user;

    public UserHelper(Object user){
        this.user = (User)user;
    }

    public String getUsername(){
        return user.getUsername();
    }
}
