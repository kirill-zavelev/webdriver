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

import java.util.concurrent.TimeUnit;

public class WebDriverYandexMailTest {

    private static final String USERNAME = "buben.vika";
    private static final String PASSWORD = "55555555vika";
    private static final String EMAIL = "buben.vika@yandex.by";
    private static final String BASE_URL = "http://passport.yandex.ru";

    private SeleniumYandexPassportLoginPage loginPage;
    private SeleniumYandexMailPage mailPage;
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
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
    public void deleteEmailFromDraft() {

        Email email = new Email();

        loginPage.clickOnUsername(USERNAME);
        mailPage = new SeleniumYandexMailPage(driver);

        boolean isEmailDeleted = mailPage
                .openMailBox()
                .createNewMail(email)
                .sendMailAsDraft()
                .openDraftsFolder()
                .checkInEmail(email)
                .clickDeleteEmail()
                .isEmailDeleted(email);

        Assert.assertTrue(isEmailDeleted);
    }

    @Test
    public void updateEmailTest() {
        Email email = new Email();
        Email expectedEmailToBeUpdated = new Email();

        loginPage.clickOnUsername(USERNAME);
        mailPage = new SeleniumYandexMailPage(driver);

        Email actualEmailToBeUpdated = mailPage
                .openMailBox()
                .createNewMail(email)
                .sendMailAsDraft()
                .openDraftsFolder()
                .openEmail(email)
                .updateEmail(expectedEmailToBeUpdated)
                .sendMail()
                .openSendFolder()
                .getActualEmailFromList(expectedEmailToBeUpdated);

        Assert.assertEquals(actualEmailToBeUpdated, expectedEmailToBeUpdated);

    }

    @Test
    public void sendEmailTest() {
        Email expectedEmail = new Email();
        mailPage = new SeleniumYandexMailPage(driver);

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
