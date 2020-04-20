package com.epam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumYandexPassportLoginPage {

    By userNameLocator = By.id("passp-field-login");
    By passwordLocator = By.xpath("//*[@id='passp-field-passwd']");
    By submitLoginLocator = By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[3]/div[2]/div/div/div[1]/form/div[3]/button[1]");
    By submitPasswordLocator = By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[3]/div[2]/div/div/form/div[2]/button[1]");

    private final WebDriver driver;

    public SeleniumYandexPassportLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public SeleniumYandexPassportLoginPage typeUserName(String username) {
        driver.findElement(userNameLocator).sendKeys(username);
        return this;
    }

    public SeleniumYandexPassportLoginPage submitLogin() {
        new WebDriverWait(driver, 1000)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(By.xpath("//*[@id='root']/div/div/footer"))));
        driver.findElement(submitLoginLocator).click();
        new WebDriverWait(driver, 30000)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(passwordLocator)));
        return this;
    }

    public SeleniumYandexPassportLoginPage typePassword(String password) {
//        new WebDriverWait(driver, 30000)
//                .until(ExpectedConditions
//                        .visibilityOf(driver.findElement(By.xpath("//*[@id='root']/div/div/footer"))));
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public SeleniumYandexPassportLoginPage submitPassword() {
        driver.findElement(submitPasswordLocator).click();
        return this;
    }
}
