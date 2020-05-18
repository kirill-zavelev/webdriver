package com.epam.webdriver.page;

import com.epam.webdriver.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InboxPage extends AbstractPage {

    @FindBy(xpath = "//*[@data-id='2']")
    private WebElement deleteEmailBtn;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private WebElement emailPreview;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftFolderBtn;

    @FindBy(xpath = "//a[@href='#sent']")
    private WebElement sendFolderBtn;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private List<WebElement> emailPreviews;

    @FindBy(className = "user-account__name")
    private WebElement loggedUsername;

    @FindBy(className = "mail-ComposeButton-Text")
    private WebElement newMailBtn;

    @FindBy(className = "composeReact__footer")
    private WebElement mailFooter;

    private static final By RECIPIENT = By.className("js-message-snippet-sender");
    private static final By SUBJECT = By.className("mail-MessageSnippet-Item_subject");
    private static final By EMAIL_BODY = By.className("js-message-snippet-firstline");

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public InboxPage clickOnUsername() {

        try {
            loggedUsername.click();
        } catch (StaleElementReferenceException sere) {
            driver.navigate().refresh();
            loggedUsername.click();
        }

        return this;
    }

    public InboxPage openDraftsFolder() {
        waitForElementToBeClickable(draftFolderBtn);
        draftFolderBtn.click();

        return this;
    }

    public InboxPage openSendFolder() {
        waitForElementToBeClickable(sendFolderBtn);
        sendFolderBtn.click();

        return this;
    }

    public InboxPage openMailCreationForm() {
        waitForElementToBeClickable(newMailBtn).click();
        waitForElementToBeClickable(mailFooter);

        return this;
    }

    public InboxPage checkEmailCheckbox(Email email) {
        By emailCheckMark = By.cssSelector("label rect");
        WebElement checkMark = findEmailPreview(emailPreviews, email).findElement(emailCheckMark);
        waitForElementToBeClickable(checkMark);
        callContextMenu(checkMark);

        return this;
    }

    public InboxPage clickDeleteEmail() {
        waitForElementToBeClickable(deleteEmailBtn);
        deleteEmailBtn.click();

        return this;
    }

    public InboxPage openEmail(Email email) {
        waitForElementToBeClickable(draftFolderBtn);
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