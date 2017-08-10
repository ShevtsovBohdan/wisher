package com.springboot.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Class that used for verification information from the form.
 */
public class WishValidation {

    public static final int WishName_MIN_SIZE = 4;
    public static final int WishName_MAX_SIZE = 45;

    @NotNull
    @Size(min = WishName_MIN_SIZE, max = WishName_MAX_SIZE,  message = "{name.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{wishName.onlyLatinSymbols}")
    public String wishName;

    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "{link.onlyLink}")
    public String link;

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
