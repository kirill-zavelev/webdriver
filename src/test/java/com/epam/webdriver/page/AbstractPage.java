package com.epam.webdriver.page;

import com.epam.webdriver.model.Email;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {

    private static final int TIME_OUT_IN_SECONDS = 10;

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private List<WebElement> emailPrev;
//    private static final By EMAIL_PREVIEW = By.xpath("//div[@data-key='box=messages-item-box']");

    private WebElement mailRecipientName;
    private WebElement mailSubjectName;
    private WebElement mailBodyName;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        driver.manage().timeouts().implicitlyWait(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    protected WebElement getBaseSpanTextLocator(String text) {
        return driver.findElement(By.xpath("//span[text()='" + text + "']"));
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
//        element(input -> {
//            if (input.isDisplayed()) {
//                input.clear();
//                input.sendKeys(text);
//            }
//        });
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public WebElement findEmailPreview(List<WebElement> emailPreviewLocator, Email email) {

        try {

            return emailPreviewLocator
                    .stream()
                    .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
                            && emailPreview.getText().contains(email.getRecipient())
                            && emailPreview.getText().contains(email.getBody()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("NSE!"));

        } catch (StaleElementReferenceException sere) {

            return emailPreviewLocator
                    .stream()
                    .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
                            && emailPreview.getText().contains(email.getRecipient())
                            && emailPreview.getText().contains(email.getBody()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("NSE!"));
        }
    }

    public Email getActualEmailFromList(Email email) {
        Email actualEmail = new Email();
        WebElement emailPreview = findEmailPreview(emailPrev, email);

        actualEmail.setBody(emailPreview.findElement(getBaseSpanTextLocator(email.getBody())).getText());
        actualEmail.setSubject(emailPreview.findElement(getBaseSpanTextLocator(email.getSubject())).getText());
        actualEmail.setRecipient(emailPreview.findElement(getBaseSpanTextLocator(email.getRecipient())).getText());

        return actualEmail;
    }

    public boolean isEmailDeleted(Email email) {
        return emailPrev
                .stream()
                .noneMatch(emailPreview -> !emailPreview.getText().contains(email.getBody()) &&
                        !emailPreview.getText().contains(email.getRecipient()) &&
                        !emailPreview.getText().contains(email.getSubject()));
    }

}