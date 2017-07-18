package com.springboot.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class WishValidation {

    private static final int WishName_MIN_SIZE = 4;
    private static final int WishName_MAX_SIZE = 45;

    @NotNull
    @Size(min = WishName_MIN_SIZE, max = WishName_MAX_SIZE,  message = "{name.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{wishName.onlyLatinSymbols}")
    private String name;

    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "{link.onlyLink}")
    private String link;

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
