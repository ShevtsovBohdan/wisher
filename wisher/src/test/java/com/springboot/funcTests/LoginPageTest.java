package com.springboot.funcTests;

import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.assertj.core.api.Assertions.assertThat;


public class LoginPageTest extends FluentTest {


    public WebDriver driver;
    private FluentWebElement signin;
    private FluentWebElement registration;
    private FluentWebElement signup;

    @Override
    public WebDriver newWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/bohdansh/IdeaProjects/chromedriver");
        driver = new ChromeDriver();
        return driver;
    }


    @Test
    public void loginPageTest() {
        goTo("http://localhost:8085/login");
        assertThat(window().title()).contains("Login Page");
        $("input").fill().with("user", "pass");
        signin.click();
        assertThat(window().title()).contains("Home Page");

    }

    @Test
    public void registrationPageTest() {
        goTo("http://localhost:8085/login");
        assertThat(window().title()).contains("Login Page");
        registration.click();
        assertThat(window().title()).contains("Registration Page");
        $("input").fill().with("user123", "pass123", "pass123");
        signup.click();
        assertThat(window().title()).contains("Registration Page");
    }
}
