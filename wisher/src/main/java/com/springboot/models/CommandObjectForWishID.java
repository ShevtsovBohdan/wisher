package com.springboot.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Class that used for receiving parameter information from the saving image requests.
 */
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
