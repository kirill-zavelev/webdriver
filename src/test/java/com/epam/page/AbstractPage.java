package com.epam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    private static final int TIME_OUT_IN_SECONDS = 10;
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    protected WebElement waitForElementToBeClickable(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement waitForVisibilityOf(By by) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
