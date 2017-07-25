package com.springboot.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.validation.constraints.Pattern;


@Component
public class RequestParameters {
    @Pattern(regexp = "[0-9]")
    private String startValue;
    @Pattern(regexp = "[0-9]+")
    private String wishID;
    @Pattern(regexp = "[0-9]+")
    private String page;

    public Integer getStartValue() {
        return Integer.parseInt(startValue);
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public Integer getWishID() {
        return Integer.parseInt(wishID);
    }

    public void setWishID(String wishID) {
        this.wishID = wishID;
    }

    public Integer getPage() {
        return Integer.parseInt(page);
    }

    public void setPage(String page) {
        this.page = page;
    }

    public RequestParameters() {
    }
}
