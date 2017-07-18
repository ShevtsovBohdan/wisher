package com.springboot.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationValidation {
    private static final int PASSWORD_MIN_SIZE = 4;
    private static final int PASSWORD_MAX_SIZE = 20;
    private static final int USERNAME_MIN_SIZE = 4;
    private static final int USERNAME_MAX_SIZE = 20;


    @NotNull(message = "{username.notnull}")
    @Size(min = USERNAME_MIN_SIZE, max = USERNAME_MAX_SIZE, message = "{username.invalidSize}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{username.onlyLatinSymbols}")
    private String username;

    @NotNull
    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = "{password.invalidSize}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{password.onlyLatinSymbols}")
    private String password;

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
}
