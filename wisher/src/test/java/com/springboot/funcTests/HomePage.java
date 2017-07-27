package com.springboot.funcTests;


import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withName;

public class HomePage extends FluentPage{
    private FluentWebElement signin;
    private FluentWebElement addWish;

    @Override
    public String getUrl() {
        return "http://localhost:8085/view?page=1";
    }

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("MyTitle");
    }

    public void fillAndSubmitForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        signin.click();
    }

    public void fillAndWishForm(String... paramsOrdered) {
        $("input").fill().with(paramsOrdered);
        addWish.click();
    }
}
