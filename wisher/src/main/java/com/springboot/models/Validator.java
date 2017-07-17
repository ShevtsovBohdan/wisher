package com.springboot.models;


import org.springframework.stereotype.Component;

@Component
public class Validator {

    private String username;
    private String password;
    private String passwordconf;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordconf() {
        return passwordconf;
    }

    public void setPasswordconf(String passwordconf) {
        this.passwordconf = passwordconf;
    }
}