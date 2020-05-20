package com.epam.webdriver.base;

import com.epam.webdriver.model.Email;
import com.epam.webdriver.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BasePage extends AbstractPage {

    @FindBy(className = "mail-ComposeButton-Text")
    private WebElement newMailBtn;

    @FindBy(className = "composeReact__footer")
    private WebElement mailFooter;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private List<WebElement> emailPreviews;

    private static final By RECIPIENT = By.className("js-message-snippet-sender");
    private static final By SUBJECT = By.className("mail-MessageSnippet-Item_subject");
    private static final By EMAIL_BODY = By.className("js-message-snippet-firstline");

    protected BasePage(WebDriver driver) {
        super(driver);
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

    public BasePage openMailCreationForm() {
        waitForElementToBeClickable(newMailBtn).click();
        waitForElementToBeClickable(mailFooter);

        return this;
    }

    public Email getActualEmailFromList(Email email) {
        Email actualEmail = new Email();
        WebElement emailPreview = findEmailPreview(emailPreviews, email);
        actualEmail.setSubject(emailPreview.findElement(SUBJECT).getText());
        actualEmail.setRecipient(emailPreview.findElement(RECIPIENT).getText());
        actualEmail.setBody(emailPreview.findElement(EMAIL_BODY).getText());

        return actualEmail;
    }
}