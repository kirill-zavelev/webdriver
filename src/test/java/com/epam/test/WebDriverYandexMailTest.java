package com.epam.test;

import com.epam.page.SeleniumYandexPassportLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverYandexMailTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--kiosk");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://passport.yandex.ru");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }

    @Test(description = "")
    public void loginTest() {
        SeleniumYandexPassportLoginPage page = new SeleniumYandexPassportLoginPage(driver);
        page.typeUserName("buben.vika")
                .submitLogin()
                .typePassword("55555555vika")
                .submitPassword();
    }
}
