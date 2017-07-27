package com.springboot.funcTests;

import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.action.WindowAction;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

public class HomePageTest extends FluentTest{
    @Page
    private HomePage page;

    public WebDriver driver;
    private WindowAction window;

    public WebDriver newWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/bohdansh/IdeaProjects/chromedriver");
        driver = new ChromeDriver();
        return driver;
    }

    @Test
    public void homePageTest(){
        goTo(page);
        page.fillAndSubmitForm("user", "pass");
        assertThat(window().title()).contains("Home Page");
        page.fillAndWishForm("wish", "http://fluentlenium.org/docs/");
        assertThat($(".fluent", withText("wiswwfwefh")).present());
    }
}
