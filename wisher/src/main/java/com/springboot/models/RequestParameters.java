package com.springboot.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class RequestParameters {
    private Integer startValue;
    private Integer wishID;
    private Integer page;

    public Integer getStartValue() {
        return startValue;
    }

    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }

    public Integer getWishID() {
        return wishID;
    }

    public void setWishID(Integer wishID) {
        this.wishID = wishID;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public RequestParameters() {
    }
}
