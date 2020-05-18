package com.epam.webdriver.base;

import com.epam.webdriver.driver.DriverSingleton;
import com.epam.webdriver.page.InboxPage;
import com.epam.webdriver.page.LoginPage;
import com.epam.webdriver.page.MailCreationPage;
import com.epam.webdriver.page.QuickActionsPanelPage;
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
    protected InboxPage inboxPage;
    protected MailCreationPage mailCreationPage;
    protected QuickActionsPanelPage quickActionsPanelPage;

    protected WebDriver driver;

    @BeforeMethod
    public void setUpBrowser() {
        driver = DriverSingleton.getDriver();
        driver.get(BASE_URL);

        loginPage = new LoginPage(driver);
        mailCreationPage = new MailCreationPage(driver);
        inboxPage = loginPage.login(USERNAME, PASSWORD).clickOnUsername();
        quickActionsPanelPage = new QuickActionsPanelPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        DriverSingleton.closeDriver();
    }
}
