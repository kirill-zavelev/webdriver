package com.epam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected By getBaseSpanTextLocator(String text) {
        return By.xpath("//span[text()='" + text + "']");
    }

    protected void clickElementWhenItDisplayed(By by) {
        driver.findElements(by).forEach(sendButton -> {
            if (sendButton.isDisplayed()) {
                sendButton.click();
            }
        });
    }

    protected void checkInFirstEmailFromTheList() {
        List<WebElement> hrefs = driver
                .findElements(By.className("mail-MessageSnippet-Checkbox js-messages-item-checkbox-visual js-skip-click-message-item"));

        for (WebElement href : hrefs) {
            href.click();
            break;
        }
    }
}