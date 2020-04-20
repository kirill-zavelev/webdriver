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

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.get("http://passport.yandex.ru");

    }
    @AfterMethod (alwaysRun = true)
    public void tearDownBrowser() {
        driver.quit();
        driver = null;
    }

    @Test(description = "verifyUrlOfStartPage")
    public void startPageHasValidUrl() {
        driver.get("http://belavia.by");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//*[@id='ibe']")));
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, "https://belavia.by/");
        driver.quit();
    }

    @Test(description = "")
    public void departureSearchFieldInput() {
//        WebElement departureSearchField = waitForElementLocatedBy(driver,
//                By.xpath("//*[@id='OriginLocation_Combobox']"));
        WebElement departureSearchField = waitForElementLocatedBy(driver,
                By.xpath("//*[@id='OriginLocation_Combobox']"));
        departureSearchField.sendKeys("Минск (MSQ), BY");
        new WebDriverWait(driver, 1000)
                .until(ExpectedConditions
                .visibilityOf(driver.findElement(By.xpath("//*[@id='footer']"))));
        String actualResult = departureSearchField.getText();
        System.out.println(actualResult);
//        String expectedResult = "Минск (MSQ), BY";
//        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(description = "")
    public void arrivalSearchFieldInput() {
        WebElement arrivalSearchField = waitForElementLocatedBy(driver,
                By.xpath("//*[@id='DestinationLocation_Combobox']"));
        arrivalSearchField.sendKeys("Москва (MOW), RU");
        new WebDriverWait(driver, 50)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(By.xpath("//*[@id='footer']"))));
        String actualResult = arrivalSearchField.getText();
        String expectedResult = "Москва (MOW), RU";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(description = "")
    public void test() {
        SeleniumYandexPassportLoginPage page = new SeleniumYandexPassportLoginPage(driver);
        page.typeUserName("buben.vika")
                .submitLogin()
                .typePassword("55555555vika")
                .submitPassword();
//        WebElement departureSearchField = waitForElementLocatedBy(driver,
//                By.xpath("//*[@id='OriginLocation_Combobox']"));
                        //        WebElement departureSearchField = waitForElementLocatedBy(driver,
                        //                By.id("passp-field-login"));
                        //        departureSearchField.sendKeys("buben.vika");
                        //        new WebDriverWait(driver, 1000)
                        //                .until(ExpectedConditions
                        //                        .visibilityOf(driver.findElement(By.xpath("//*[@id='root']/div/div/footer"))));
                        //        driver.findElement(
                        //                By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[3]/div[2]/div/div/div[1]/form/div[3]/button[1]")).click();
                        //        WebElement passwordField = waitForElementLocatedBy(driver,
                        //                By.id("passp-field-passwd"));
                        //        passwordField.sendKeys("55555555vika");
                        //        driver.findElement(
                        //                By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[3]/div[2]/div/div/form/div[2]/button[1]")).click();
                        //        Thread.sleep(1000);
                        //        driver.getCurrentUrl();
//        String actualResult = departureSearchField.getText();
//        String expectedResult = "Минск";
//        Assert.assertEquals(actualResult, expectedResult);
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
