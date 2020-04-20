package com.epam.test;

import com.epam.page.SeleniumYandexPassportLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class WebDriverBelaviaTest {

    private static final String USERNAME = "buben.vika";
    private static final String PASSWORD = "55555555vika";
    private static final String EMAIL = "buben.vika@yandex.by";
    private static final String BASE_URL = "http://passport.yandex.ru";

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }

    @Test
    public void loginTest() {
        SeleniumYandexPassportLoginPage page = new SeleniumYandexPassportLoginPage(driver);

        String actualEmail = page.typeUserName(USERNAME)
                .submitLogin()
                .typePassword(PASSWORD)
                .submitPassword()
                .clickOnUsername(USERNAME)
                .getActualEmail(EMAIL);

        Assert.assertEquals(actualEmail, EMAIL);
    }

}
