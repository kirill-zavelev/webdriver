package com.epam.webdriver.page;

import com.epam.webdriver.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InboxPage extends AbstractPage {

    @FindBy(xpath = "//a[text()='Почта']")
    private WebElement mailPage;

    @FindBy(xpath = "//span[contains(@class, 'item-title-delete')]")
    private WebElement deleteEmail;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private WebElement emailPreview;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftFolder;

    @FindBy(xpath = "//a[@href='#sent']")
    private WebElement sendFolder;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private List<WebElement> emailPreviews;

    private static final By RECIPIENT = By.className("js-message-snippet-sender");
    private static final By SUBJECT = By.className("mail-MessageSnippet-Item_subject");
    private static final By EMAIL_BODY = By.className("js-message-snippet-firstline");

    public InboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public InboxPage openMailBox() {
        waitForElementToBeClickable(mailPage).click();

        return this;
    }

    public InboxPage openDraftsFolder() {
        waitForElementToBeClickable(draftFolder);
        draftFolder.click();

        return this;
    }

    public InboxPage openSendFolder() {
        waitForElementToBeClickable(sendFolder);
        sendFolder.click();

        return this;
    }

    public InboxPage checkInEmail(Email email) {
        By emailCheckMark = By.cssSelector("label rect");
        WebElement checkMark = findEmailPreview(emailPreviews, email).findElement(emailCheckMark);
        waitForElementToBeClickable(checkMark);
        checkMark.click();

        return this;
    }

    public InboxPage clickDeleteEmail() {
        waitForElementToBeClickable(deleteEmail);
        deleteEmail.click();

        return this;
    }

    public InboxPage openEmail(Email email) {
        waitForElementToBeClickable(draftFolder);
        findEmailPreview(emailPreviews, email).click();

        return this;
    }



    public Email getActualEmailFromList(Email email) {
        Email actualEmail = new Email();
        WebElement emailPreview = findEmailPreview(emailPreviews, email);

        actualEmail.setRecipient(emailPreview.findElement(RECIPIENT).getText());
        actualEmail.setSubject(emailPreview.findElement(SUBJECT).getText());
        actualEmail.setBody(emailPreview.findElement(EMAIL_BODY).getText());

        return actualEmail;
    }


    public boolean isEmailDeleted(Email email) {
        return emailPreviews
                .stream()
                .noneMatch(emailPreview -> !emailPreview.getText().contains(email.getBody()) &&
                        !emailPreview.getText().contains(email.getRecipient()) &&
                        !emailPreview.getText().contains(email.getSubject()));
    }
}
