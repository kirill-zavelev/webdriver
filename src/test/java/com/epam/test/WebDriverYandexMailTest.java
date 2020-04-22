package com.epam.test;

import com.epam.model.Email;
import com.epam.page.SeleniumYandexMailPage;
import com.epam.page.SeleniumYandexPassportLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverYandexMailTest {

    private static final String USERNAME = "buben.vika";
    private static final String PASSWORD = "55555555vika";
    private static final String EMAIL = "buben.vika@yandex.by";
    private static final String BASE_URL = "http://passport.yandex.ru";

    private SeleniumYandexPassportLoginPage loginPage;
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        loginPage = new SeleniumYandexPassportLoginPage(driver);
        loginPage.typeUserName(USERNAME)
                .submitLogin()
                .typePassword(PASSWORD)
                .submitPassword();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }

    @Test
    public void loginTest() {

        String actualEmail = loginPage
                .clickOnUsername(USERNAME)
                .getActualEmail(EMAIL);

        Assert.assertEquals(actualEmail, EMAIL);
    }

    @Test
    public void sendEmailToDraftTest() {
        Email expectedEmail = new Email();
        SeleniumYandexMailPage mailPage = new SeleniumYandexMailPage(driver);

        loginPage.clickOnUsername(USERNAME);

        Email actualEmail = mailPage
                .openMailBox()
                .createNewMail(expectedEmail)
                .sendMailAsDraft()
                .openDraftsFolder()
                .getActualEmailFromList(expectedEmail);

        Assert.assertEquals(actualEmail, expectedEmail);
    }

    @Test
    public void sendEmailTest() {
        Email expectedEmail = new Email();
        SeleniumYandexMailPage mailPage = new SeleniumYandexMailPage(driver);

        loginPage.clickOnUsername(USERNAME);

        Email actualEmail = mailPage
                .openMailBox()
                .createNewMail(expectedEmail)
                .sendMail()
                .openSendFolder()
                .getActualEmailFromList(expectedEmail);

        Assert.assertEquals(actualEmail, expectedEmail);
    }

    @Test
    public void logoutTest() {

        boolean isPasswordInputInteractable = loginPage
                .clickOnUsername(USERNAME)
                .clickOnLogoutLink()
                .isPasswordInputDisplayed();

        Assert.assertTrue(isPasswordInputInteractable);
    }
}
