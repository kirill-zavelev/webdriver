package com.epam.page;

import com.epam.model.Email;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {

    private static final int TIME_OUT_IN_SECONDS = 10;
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        driver.manage().timeouts().implicitlyWait(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
    }

    protected WebElement waitForElementToBeClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement waitForVisibilityOf(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected By getBaseSpanTextLocator(String text) {
        return By.xpath("//span[text()='" + text + "']");
    }

    protected void clickElementWhenItDisplayed(By by) {

        try {
            driver.findElements(by).forEach(button -> {
                if (button.isDisplayed()) {
                    button.click();
                }
            });
        } catch (StaleElementReferenceException sere) {
            clickElementWhenItDisplayed(by);
        }

    }

    protected void sendKeysWhenInputInteractable(By by, String text) {
        driver.findElements(by).forEach(input -> {
            if (input.isDisplayed()) {
                input.clear();
                input.sendKeys(text);
            }
        });
    }

    protected WebElement findEmailPreview(By emailPreviewLocator, Email email) {

        try {

            return driver.findElements(emailPreviewLocator)
                    .stream()
                    .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
                            && emailPreview.getText().contains(email.getSubject())
                            && emailPreview.getText().contains(email.getBody()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("NSE!"));

        } catch (StaleElementReferenceException sere) {

            return driver.findElements(emailPreviewLocator)
                    .stream()
                    .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
                            && emailPreview.getText().contains(email.getSubject())
                            && emailPreview.getText().contains(email.getBody()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("NSE!"));
        }
    }

}