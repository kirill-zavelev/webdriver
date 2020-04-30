package com.epam.webdriver.page;

import com.epam.webdriver.model.Email;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MailCreationPage extends AbstractPage {

    @FindBy(className = "mail-ComposeButton-Text")
    private WebElement newMail;

    @FindBy(className = "composeReact__footer")
    private WebElement mailFooter;

    @FindBy(xpath = "//div[@is='x-bubbles']")
    private WebElement sendTo;

    @FindBy(name = "subject")
    private WebElement mailSubject;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement mailBody;

    @FindBy(xpath = "//button[contains(@class, 'btn--close')]")
    private List<WebElement> saveMailToDraft;

    @FindBy(xpath = "//button[contains(@class, 'Button_action')]")
    private List<WebElement> sendEmail;

    public MailCreationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MailCreationPage createNewMail(Email email) {
        waitForElementToBeClickable(newMail).click();
        waitForElementToBeClickable(mailFooter);
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

    public MailCreationPage updateEmail(Email email) {
        fulfillEmail(email);

        return this;
    }

    public MailCreationPage sendMailAsDraft() {
        clickElementWhenItDisplayed(saveMailToDraft);

        return this;
    }

    public MailCreationPage sendMail() {
        clickElementWhenItDisplayed(sendEmail);

        return this;
    }
}
