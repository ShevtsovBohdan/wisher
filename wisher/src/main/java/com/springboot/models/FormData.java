package com.springboot.models;

import org.springframework.stereotype.Component;

@Component
public class FormData {
    private String localLink;
    private String wishID;

    public String getLocalLink() {
        return localLink;
    }

    public void setLocalLink(String localLink) {
        this.localLink = localLink;
    }

    public String getWishID() {
        return wishID;
    }

    public void setWishID(String wishID) {
        this.wishID = wishID;
    }
}
