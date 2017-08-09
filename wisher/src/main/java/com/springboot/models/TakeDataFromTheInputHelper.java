package com.springboot.models;

import org.springframework.stereotype.Component;

/**
 * Class that used for receiving information from the user's registration form
 */
//TODO it is not a component. It is a POJO or DTO
@Component
//TODO change name of class (e.g. UserDataHolder)
public class TakeDataFromTheInputHelper {

    private String username;
    private String password;
//TODO rename to passwordConfirm
    private String passwordconf;



    //TODO move to other class (SearchWish)
    private String search;




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

    public String getSearchRequest() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Boolean isSearchRequestEmpty() {
        return search == "" ? true : false;
    }
}