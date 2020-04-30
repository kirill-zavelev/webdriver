package com.epam.webdriver.page;

import com.epam.webdriver.model.Email;
import org.openqa.selenium.NoSuchElementException;
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

    protected void clickElementWhenItDisplayed(List<WebElement> elements) {

        try {
            elements.forEach(button -> {
                if (button.isDisplayed()) {
                    button.click();
                }
            });
        } catch (StaleElementReferenceException sere) {
            clickElementWhenItDisplayed(elements);
        }

    }

    protected void sendKeysWhenInputInteractable(WebElement element, String text) {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public WebElement findEmailPreview(List<WebElement> emailPreviews, Email email) {
            driver.navigate().refresh();

            return emailPreviews
                    .stream()
                    .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
                            && emailPreview.getText().contains(email.getRecipient())
                            && emailPreview.getText().contains(email.getBody()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("NSE!"));
    }

}