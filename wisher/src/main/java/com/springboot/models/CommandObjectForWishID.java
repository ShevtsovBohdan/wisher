package com.springboot.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class CommandObjectForWishID {
    @NotNull
    @Pattern(regexp = "^\\d+$", message = "{wishID.onlyNumbers}")
    private String wishID;

    public String getWishID() {
        return wishID;
    }

    public void setWishID(String wishID) {
        this.wishID = wishID;
    }
}
