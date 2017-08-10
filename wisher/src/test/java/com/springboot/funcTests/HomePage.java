package com.springboot.funcTests;


import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withName;

public class HomePage extends FluentPage{

    @Override
    public String getUrl() {
        return "http://localhost:8080/view?page=1";
    }

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("Home Page");
    }

    public FluentWebElement inputUsernameField() {
        return el("input", withName("username"));
    }

    public FluentWebElement inputPasswordField() {
        return el("input", withName("password"));
    }

    public FluentWebElement inputWishField() {
       return el("input", withName("wishName"));
    }

    public FluentWebElement inputLinkField() {
        return el("input", withName("link"));
    }

    public FluentWebElement signInButton() {
        return el("button", withName("signin"));
    }

    public FluentWebElement addWishButton() {
        return el("button", withName("addWish"));
    }

    public FluentWebElement deleteWishButton(String deteWishName) {
        return el("button", withName(deteWishName));
    }
}
