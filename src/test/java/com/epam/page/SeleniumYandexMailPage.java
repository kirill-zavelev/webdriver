package com.epam.page;

import com.epam.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumYandexMailPage extends AbstractPage {


    private static final By MAIL_PAGE_LOCATOR = By.xpath("//a[text()='Почта']");
    private static final By NEW_MAIL_LOCATOR = By.className("mail-ComposeButton-Text");
    private static final By SEND_TO_LOCATOR = By.xpath("//div[@is='x-bubbles']");
    private static final By MAIL_SUBJECT_LOCATOR = By.name("subject");
    private static final By MAIL_BODY_LOCATOR = By.xpath("//div[@role='textbox']");
    private static final By SAVE_MAIL_TO_DRAFT_LOCATOR = By.xpath("//button[@class='controlButtons__btn controlButtons__btn--close']");
    private static final By DELETE_EMAIL_BUTTON = By.xpath("//span[text()='Удалить']");
    private static final By MAIL_FOOTER_LOCATOR = By.xpath("//a[@id='cke_37']");
    private static final By SEND_EMAIL_BUTTON = By.xpath("//button[contains(@class, 'ComposeControlPanelButton-Button_action')]");

    private static final By EMAIL_PREVIEW_LOCATOR = By.xpath("//div[@data-key='box=messages-item-box']");
    private static final By EMAIL_CHECK_MARK = By.cssSelector("label rect");

    private static final By DRAFT_FOLDER_LOCATOR = By.xpath("//a[@data-title='Черновики']");
    private static final By SEND_FOLDER_LOCATOR = By.xpath("//a[@data-title='Отправленные']");
    private By mailRecipientNameLocator;
    private By mailSubjectNameLocator;
    private By mailBodyNameLocator;

    public SeleniumYandexMailPage(WebDriver driver) {
        super(driver);
    }

    public SeleniumYandexMailPage openMailBox() {
        waitForElementToBeClickable(MAIL_PAGE_LOCATOR).click();

        return this;
    }

    public SeleniumYandexMailPage createNewMail(Email email) {
        waitForElementToBeClickable(NEW_MAIL_LOCATOR).click();
        waitForElementToBeClickable(MAIL_FOOTER_LOCATOR);
        fulfillEmail(email);

        return this;
    }

    public SeleniumYandexMailPage sendMailAsDraft() {
        clickElementWhenItDisplayed(SAVE_MAIL_TO_DRAFT_LOCATOR);

        return this;
    }

    public SeleniumYandexMailPage sendMail() {
        clickElementWhenItDisplayed(SEND_EMAIL_BUTTON);

        return this;
    }

    public SeleniumYandexMailPage openDraftsFolder() {
        waitForElementToBeClickable(DRAFT_FOLDER_LOCATOR);
        driver.findElement(DRAFT_FOLDER_LOCATOR).click();

        return this;
    }

    public SeleniumYandexMailPage openSendFolder() {
        waitForElementToBeClickable(SEND_FOLDER_LOCATOR);
        driver.findElement(SEND_FOLDER_LOCATOR).click();

        return this;
    }

    public Email getActualEmailFromList(Email email) {
        Email actualEmail = new Email();

        mailRecipientNameLocator = getBaseSpanTextLocator(email.getRecipient());
        mailSubjectNameLocator = getBaseSpanTextLocator(email.getSubject());
        mailBodyNameLocator = getBaseSpanTextLocator(email.getBody());

        waitForElementToBeClickable(DRAFT_FOLDER_LOCATOR);
        waitForVisibilityOf(mailRecipientNameLocator);
        waitForVisibilityOf(mailSubjectNameLocator);
        waitForVisibilityOf(mailBodyNameLocator);

        actualEmail.setRecipient(driver.findElement(mailRecipientNameLocator).getText());
        actualEmail.setSubject(driver.findElement(mailSubjectNameLocator).getText());
        actualEmail.setBody(driver.findElement(mailBodyNameLocator).getText());

        return actualEmail;
    }

    public SeleniumYandexMailPage checkInEmail(Email email) {
        WebElement checkMark = findEmailPreview(EMAIL_PREVIEW_LOCATOR, email).findElement(EMAIL_CHECK_MARK);
        waitForElementToBeClickable(EMAIL_CHECK_MARK);
        checkMark.click();

        return this;
    }

    public SeleniumYandexMailPage clickDeleteEmail() {
        waitForElementToBeClickable(DELETE_EMAIL_BUTTON);
        driver.findElement(DELETE_EMAIL_BUTTON).click();

        return this;
    }

    public boolean isEmailDeleted(Email email) {
        return driver.findElements(EMAIL_PREVIEW_LOCATOR)
                .stream()
                .noneMatch(emailPreview -> !emailPreview.getText().contains(email.getBody()) &&
                        !emailPreview.getText().contains(email.getRecipient()) &&
                        !emailPreview.getText().contains(email.getSubject()));

    }

    public SeleniumYandexMailPage openEmail(Email email) {
        waitForElementToBeClickable(DRAFT_FOLDER_LOCATOR);
        findEmailPreview(EMAIL_PREVIEW_LOCATOR, email).click();

        return this;
    }

    public SeleniumYandexMailPage updateEmail(Email email) {
        fulfillEmail(email);

        return this;
    }

    private void fulfillEmail(Email email) {
        waitForElementToBeClickable(SEND_TO_LOCATOR);
        waitForElementToBeClickable(MAIL_SUBJECT_LOCATOR);
        waitForElementToBeClickable(MAIL_BODY_LOCATOR);

        sendKeysWhenInputInteractable(SEND_TO_LOCATOR, email.getRecipient());
        sendKeysWhenInputInteractable(MAIL_SUBJECT_LOCATOR, email.getSubject());
        sendKeysWhenInputInteractable(MAIL_BODY_LOCATOR, email.getBody());
    }
}
