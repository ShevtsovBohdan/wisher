package com.springboot.models;

import org.springframework.stereotype.Component;
import javax.validation.constraints.Pattern;

/**
 * Class that used for receiving parameter information from the user's requests.
 */
public class RequestParameters {
    @Pattern(regexp = "[0-9]")
    private String startValue;
    @Pattern(regexp = "[0-9]+")
    private String wishID;
    @Pattern(regexp = "[0-9]+")
    private String page;

    private String deleteWishId;

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

    public Integer getDeleteWishId() {
        return Integer.parseInt(deleteWishId);
    }

    public void setDeleteWishId(String deleteWishId) {
        this.deleteWishId = deleteWishId;
    }
}
