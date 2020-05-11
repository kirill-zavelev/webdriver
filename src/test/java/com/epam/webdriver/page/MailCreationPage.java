package com.epam.webdriver.page;

import com.epam.webdriver.model.Email;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MailCreationPage extends AbstractPage {

    @FindBy(className = "mail-ComposeButton-Text")
    private WebElement newMailBtn;

    @FindBy(className = "composeReact__footer")
    private WebElement mailFooter;

    @FindBy(xpath = "//div[@is='x-bubbles']")
    private WebElement sendTo;

    @FindBy(name = "subject")
    private WebElement mailSubject;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement mailBody;

    @FindBy(xpath = "//button[contains(@class, 'btn--close')]")
    private List<WebElement> saveMailToDraftBtn;

    @FindBy(xpath = "//button[contains(@class, 'Button_action')]")
    private List<WebElement> sendEmailBtn;

    public MailCreationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MailCreationPage createNewMail(Email email) {
        waitForElementToBeClickable(newMailBtn).click();
        waitForElementToBeClickable(mailFooter);
        fillEmail(email);

        return this;
    }

    public MailCreationPage fillEmail(Email email) {
        waitForElementToBeClickable(sendTo);
        sendKeysWhenInputIntractable(sendTo, email.getRecipient());

        waitForElementToBeClickable(mailSubject);
        sendKeysWhenInputIntractable(mailSubject, email.getSubject());

        waitForElementToBeClickable(mailBody);
        sendKeysWhenInputIntractable(mailBody, email.getBody());

        return this;
    }

    public MailCreationPage sendMailAsDraft() {
        clickElementWhenItDisplayed(saveMailToDraftBtn);

        return this;
    }

    public MailCreationPage sendMail() {
        clickElementWhenItDisplayed(sendEmailBtn);

        return this;
    }
}
