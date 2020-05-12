package com.epam.webdriver.base;

import com.epam.webdriver.page.InboxPage;
import com.epam.webdriver.page.LoginPage;
import com.epam.webdriver.page.MailCreationPage;
import com.epam.webdriver.propertyloader.PropertyLoader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import javax.sound.sampled.Port;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static final String USERNAME = PropertyLoader.loadProperty("user.name");
    protected static final String PASSWORD = PropertyLoader.loadProperty("user.password");
    protected static final String EMAIL = PropertyLoader.loadProperty("user.send.from");
    protected static final String BASE_URL = PropertyLoader.loadProperty("base.url");

    protected LoginPage loginPage;
    protected InboxPage inboxPage;
    protected MailCreationPage mailCreationPage;

    protected WebDriver driver;

    @Parameters({"browser", "port"})
    @BeforeMethod
    public void setUpBrowser(String browser, String port) throws MalformedURLException {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL("http://192.168.100.15:".concat(port).concat("/wd/hub")), capability);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        mailCreationPage = new MailCreationPage(driver);
        inboxPage = loginPage.login(USERNAME, PASSWORD).clickOnUsername();

    }
//    @BeforeMethod(alwaysRun = true)
//    public void setUpBrowser() throws MalformedURLException {
//
//        ChromeOptions options = new ChromeOptions();
//
//        options.addArguments("--disable-notifications");
//        driver = new RemoteWebDriver(new URL("http://192.168.100.15:4444/wd/hub"), options);
//
//        driver.manage().window().maximize();
//        driver.get(BASE_URL);
//
//        loginPage = new LoginPage(driver);
//        mailCreationPage = new MailCreationPage(driver);
//
//        inboxPage = loginPage.login(USERNAME, PASSWORD).clickOnUsername();
//    }

//    @BeforeMethod(alwaysRun = true)
//    public void setUpBrowser() throws MalformedURLException {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        driver = new RemoteWebDriver(new URL("http://192.168.100.15:4444/wd/hub"), options);
//
//        driver.manage().window().maximize();
//        driver.get(BASE_URL);
//
//        loginPage = new LoginPage(driver);
//        inboxPage = new InboxPage(driver);
//        mailCreationPage = new MailCreationPage(driver);
//
//        loginPage.setUserName(USERNAME)
//                .clickLogin()
//                .setPassword(PASSWORD)
//                .clickPassword()
//                .clickOnUsername();
//    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        driver.close();
        driver = null;
    }
}
