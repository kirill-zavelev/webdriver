package com.epam.webdriver.base;

import com.epam.webdriver.page.InboxPage;
import com.epam.webdriver.page.LoginPage;
import com.epam.webdriver.page.MailCreationPage;
import com.epam.webdriver.propertyloader.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected static final String USERNAME = PropertyLoader.loadProperty("user.name");
    protected static final String PASSWORD = PropertyLoader.loadProperty("user.password");
    protected static final String EMAIL = PropertyLoader.loadProperty("user.send.from");
    protected static final String BASE_URL = PropertyLoader.loadProperty("base.url");

    protected LoginPage loginPage;
    protected InboxPage inboxPage;
    protected MailCreationPage mailCreationPage;

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
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
