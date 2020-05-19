package com.epam.webdriver.base;

import com.epam.webdriver.driver.DriverSingleton;
import com.epam.webdriver.page.*;
import com.epam.webdriver.utils.PropertyLoader;
import com.epam.webdriver.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest {

    protected static final String USERNAME = PropertyLoader.loadProperty("user.name");
    protected static final String PASSWORD = PropertyLoader.loadProperty("user.password");
    protected static final String EMAIL = PropertyLoader.loadProperty("user.send.from");
    protected static final String BASE_URL = PropertyLoader.loadProperty("base.url");

    protected LoginPage loginPage;
    protected StartPage startPage;
    protected QuickActionsPanelPage quickActionsPanelPage;
    protected InboxPage inboxPage;
    protected MailCreationPage mailCreationPage;
    protected DraftPage draftPage;
    protected SendPage sendPage;

    protected WebDriver driver;

    @BeforeMethod
    public void setUpBrowser() {
        driver = DriverSingleton.getDriver();
        driver.get(BASE_URL);

        loginPage = new LoginPage(driver);
        mailCreationPage = new MailCreationPage(driver);
        draftPage = new DraftPage(driver);
        inboxPage = new InboxPage(driver);
        sendPage = new SendPage(driver);
        quickActionsPanelPage = loginPage.login(USERNAME, PASSWORD).clickOnUsername();
//        startPage = loginPage.login(USERNAME,PASSWORD);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        DriverSingleton.closeDriver();
    }
}
