package com.epam.page;

import com.epam.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SeleniumYandexMailPage extends AbstractPage {


    @FindBy(xpath = "//a[text()='Почта']")
    private WebElement mailPage;

    @FindBy(className = "mail-ComposeButton-Text")
    private WebElement newMail;

    @FindBy(xpath = "//div[@is='x-bubbles']")
    private WebElement sendTo;

    @FindBy(name = "subject")
    private WebElement mailSubject;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement mailBody;

    @FindBy(xpath = "//button[@class='controlButtons__btn controlButtons__btn--close']")
    private List<WebElement> saveMailToDraft;

    @FindBy(xpath = "//span[text()='Удалить']")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "//a[@id='cke_37']")
    private WebElement mailFooter;

    @FindBy(xpath = "//button[contains(@class, 'ComposeControlPanelButton-Button_action')]")
    private List<WebElement> sendEmailButton;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private List<WebElement> emailPreviews;

    @FindBy(xpath = "//a[@data-title='Черновики']")
    private WebElement draftFolder;
    
    @FindBy(xpath = "//a[@data-title='Отправленные']")
    private WebElement sendFolder;
    
    private By mailRecipientName;
    private By mailSubjectName;
    private By mailBodyName;

    public SeleniumYandexMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SeleniumYandexMailPage openMailBox() {
        waitForElementToBeClickable(mailPage).click();

        return this;
    }

    public SeleniumYandexMailPage createNewMail(Email email) {
        waitForElementToBeClickable(newMail).click();
        waitForElementToBeClickable(mailFooter);
        fulfillEmail(email);

        return this;
    }

    public SeleniumYandexMailPage sendMailAsDraft() {
        clickElementWhenItDisplayed(saveMailToDraft);

        return this;
    }

    public SeleniumYandexMailPage sendMail() {
        clickElementWhenItDisplayed(sendEmailButton);

        return this;
    }

    public SeleniumYandexMailPage openDraftsFolder() {
        waitForElementToBeClickable(draftFolder);
        draftFolder.click();

        return this;
    }

    public SeleniumYandexMailPage openSendFolder() {
        waitForElementToBeClickable(sendFolder);
        sendFolder.click();

        return this;
    }

    public Email getActualEmailFromList(Email email) {
        Email actualEmail = new Email();

        mailRecipientName = getBaseSpanTextLocator(email.getRecipient());
        mailSubjectName = getBaseSpanTextLocator(email.getSubject());
        mailBodyName = getBaseSpanTextLocator(email.getBody());

        waitForElementToBeClickable(draftFolder);
        waitForVisibilityOf(mailRecipientName);
        waitForVisibilityOf(mailSubjectName);
        waitForVisibilityOf(mailBodyName);

        actualEmail.setRecipient(driver.findElement(mailRecipientName).getText());
        actualEmail.setSubject(driver.findElement(mailSubjectName).getText());
        actualEmail.setBody(driver.findElement(mailBodyName).getText());

        return actualEmail;
    }

    public SeleniumYandexMailPage checkInEmail(Email email) {
        WebElement checkMark = findEmail(email).findElement(By.cssSelector("label rect"));
        waitForElementToBeClickable(checkMark).click();

        return this;
    }

    public SeleniumYandexMailPage clickDeleteEmail() {
        waitForElementToBeClickable(deleteEmailButton);
        deleteEmailButton.click();

        return this;
    }

    public boolean isEmailDeleted(Email email) {
        return emailPreviews
                .stream()
                .noneMatch(emailPreview -> !emailPreview.getText().contains(email.getBody()) &&
                        !emailPreview.getText().contains(email.getRecipient()) &&
                        !emailPreview.getText().contains(email.getSubject()));

    }

    public SeleniumYandexMailPage openEmail(Email email) {
        waitForElementToBeClickable(draftFolder);
        findEmail(email).click();

        return this;
    }

    public SeleniumYandexMailPage updateEmail(Email email) {
        fulfillEmail(email);

        return this;
    }

    private void fulfillEmail(Email email) {
        waitForElementToBeClickable(sendTo);
        waitForElementToBeClickable(mailSubject);
        waitForElementToBeClickable(mailBody);

        sendKeysWhenInputInteractable(sendTo, email.getRecipient());
        sendKeysWhenInputInteractable(mailSubject, email.getSubject());
        sendKeysWhenInputInteractable(mailBody, email.getBody());
    }

    private WebElement findEmail(Email email) {
        return emailPreviews
                .stream()
                .filter(emailPreview -> emailPreview.getText().contains(email.getSubject())
                        && emailPreview.getText().contains(email.getSubject())
                        && emailPreview.getText().contains(email.getBody()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("NSE!"));
    }
}
