package com.springboot.funcTests;

import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.action.WindowAction;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withId;
import static org.fluentlenium.core.filter.FilterConstructor.withName;
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
    public void homePageAddOneWishTest(){
        goTo(page);
        page.inputUsernameField().fill().with("user");
        page.inputPasswordField().fill().with("pass");
        page.signInButton().click();
        assertThat(window().title()).contains("Home Page");
        page.inputWishField().fill().with("wish");
        page.inputLinkField().fill().with("http://fluentlenium.org/docs/");
        page.addWishButton().click();
        assertThat((el("td", withId("wish"))).displayed());
    }

    @Test
    public void homePageAddThreeWishTest(){
        goTo(page);
        page.inputUsernameField().fill().with("user");
        page.inputPasswordField().fill().with("pass");
        page.signInButton().click();
        assertThat(window().title()).contains("Home Page");

        addWish("firstWish", "http://fluentlenium.org/docs/");
        page.addWishButton().click();

        addWish("secondWish", "https://translate.google.com/#en/ru/select");
        page.addWishButton().click();

        addWish("thirdWish", "https://g00glen00b.be/spring-boot-selenium/");
        page.addWishButton().click();

        assertThat((el("td", withId("firstWish"))).displayed());
        assertThat((el("td", withId("secondWish"))).displayed());
        assertThat((el("td", withId("thirdWish"))).displayed());
        assertThat((el("td", withText("http://fluentlenium.org/docs/"))).displayed());
        assertThat((el("td", withText("https://translate.google.com/#en/ru/select"))).displayed());
        assertThat((el("td", withText("https://g00glen00b.be/spring-boot-selenium/"))).displayed());
    }

    @Test
    public void homePageAddThreeWishAndDeleteOneTest(){
        goTo(page);
        page.inputUsernameField().fill().with("user");
        page.inputPasswordField().fill().with("pass");
        page.signInButton().click();
        assertThat(window().title()).contains("Home Page");

        addWish("firstWish", "http://fluentlenium.org/docs/");
        page.addWishButton().click();

        addWish("secondWish", "https://translate.google.com/#en/ru/select");
        page.addWishButton().click();

        addWish("thirdWish", "https://g00glen00b.be/spring-boot-selenium/");
        page.addWishButton().click();

        assertThat((el("td", withId("firstWish"))).displayed());
        assertThat((el("td", withId("secondWish"))).displayed());
        assertThat((el("td", withId("thirdWish"))).displayed());
        assertThat((el("td", withText("http://fluentlenium.org/docs/"))).displayed());
        assertThat((el("td", withText("https://translate.google.com/#en/ru/select"))).displayed());
        assertThat((el("td", withText("https://g00glen00b.be/spring-boot-selenium/"))).displayed());

        FluentList<FluentWebElement> listNamesBeforeDeleting = $("td", withName("wishName"));
        FluentList<FluentWebElement> listLinksBeforeDeleting = $("td", withName("wishLink"));

        assertThat(listLinksBeforeDeleting.get(0).id() == "http://fluentlenium.org/docs/");
        assertThat(listNamesBeforeDeleting.get(0).id() == "firstWish");

        page.deleteWishButton("thirdWish").click();

        FluentList<FluentWebElement> listAfterDeleting = $("td", withName("wishName"));

        assertThat(listNamesBeforeDeleting.size() - listAfterDeleting.size() == 1);

        Iterator<FluentWebElement> iterator = listAfterDeleting.iterator();
        while(iterator.hasNext()){
            FluentWebElement currentElement = iterator.next();
            assertThat(currentElement.id() != "thirdWish");
        }
//        assertThat((el("td", withId("thirdWish"))).displayed());

    }

    @Test
    public void addingIncorrectDataToTheFormTest(){
        goTo(page);
        page.inputUsernameField().fill().with("user");
        page.inputPasswordField().fill().with("pass");
        page.signInButton().click();
        assertThat(window().title()).contains("Home Page");

        addWish("rr", "http://fluentlenium.org/docs/");
        page.addWishButton().click();

        assertThat(el("p", withText("The size of the wish should be between 4 and 20 symbols")).displayed());
        assertThat(page.inputLinkField().text() == "http://fluentlenium.org/docs/");


    }




    private void addWish(String wishName, String link){
        page.inputWishField().fill().with(wishName);
        page.inputLinkField().fill().with(link);
    }
}
