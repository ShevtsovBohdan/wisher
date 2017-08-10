package com.springboot.models;

/**
 * Class that used for receiving information from the user's registration form
 */
public class SearchDTO {

    private String searchWish;

    public String getSearchRequest() {
        return searchWish;
    }

    public void setSearchWish(String searchWish) {
        this.searchWish = searchWish;
    }

    public Boolean isSearchRequestEmpty() {
        return searchWish == "" ? true : false;
    }
}