package com.epam.webdriver.base;

import com.epam.webdriver.page.InboxPage;
import com.epam.webdriver.page.LoginPage;
import com.epam.webdriver.page.MailCreationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static final String USERNAME = "buben.vika";
    protected static final String PASSWORD = "55555555vika";
    protected static final String EMAIL = "buben.vika@yandex.by";
    protected static final String BASE_URL = "http://passport.yandex.ru";

    protected LoginPage loginPage;
    protected InboxPage inboxPage;
    protected MailCreationPage mailCreationPage;

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
        mailCreationPage = new MailCreationPage(driver);

        loginPage.setUserName(USERNAME)
                .clickLogin()
                .setPassword(PASSWORD)
                .clickPassword()
                .clickOnUsername();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        driver.close();
        driver = null;
    }
}
