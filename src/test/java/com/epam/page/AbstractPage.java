package com.epam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
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

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForVisibilityOf(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected By getBaseSpanTextLocator(String text) {
        return By.xpath("//span[text()='" + text + "']");
    }

    protected void clickElementWhenItDisplayed(List<WebElement> buttons) {
        try {
            buttons.forEach(button -> {
                if (button.isDisplayed()) {
                    button.click();
                }
            });
        } catch (StaleElementReferenceException sere) {
            clickElementWhenItDisplayed(buttons);
        }

    }

    protected void sendKeysWhenInputInteractable(WebElement input, String text) {
        waitForElementToBeClickable(input);
        input.clear();
        input.sendKeys(text);
    }

//    protected WebElement findEmailPreview(By emailPreviewLocator, Email email) {
//
//        try {
//
//            return driver.findElements(emailPreviewLocator)
//                    .stream()
//                    .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
//                            && emailPreview.getText().contains(email.getSubject())
//                            && emailPreview.getText().contains(email.getBody()))
//                    .findFirst()
//                    .orElseThrow(() -> new NoSuchElementException("NSE!"));
//
//        } catch (StaleElementReferenceException sere) {
//
//            return driver.findElements(emailPreviewLocator)
//                    .stream()
//                    .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
//                            && emailPreview.getText().contains(email.getSubject())
//                            && emailPreview.getText().contains(email.getBody()))
//                    .findFirst()
//                    .orElseThrow(() -> new NoSuchElementException("NSE!"));
//        }
//    }

}