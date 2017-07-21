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
    public String name;

    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "{link.onlyLink}")
    public String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
