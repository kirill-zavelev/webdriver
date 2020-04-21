package com.epam.page;

import com.epam.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumYandexMailPage extends AbstractPage {

    private static final By MAIL_PAGE_LOCATOR = By.xpath("/html/body/div[4]/ul/ul/li[1]/a");
    private static final By NEW_MAIL_LOCATOR = By.className("mail-ComposeButton-Text");
    private static final By SEND_TO_LOCATOR = By.xpath("(//div[@class='composeYabbles'])[1]");
    private static final By MAIL_SUBJECT_LOCATOR = By.name("subject");
    private static final By MAIL_BODY_LOCATOR = By.xpath("//*[@id='cke_1_contents']/div");
    private static final By SAVE_MAIL_TO_DRAFT_LOCATOR = By.xpath("//button[@class='controlButtons__btn controlButtons__btn--close']");
    private static final By MAIL_FOOTER_LOCATOR = By.xpath("//a[@id='cke_37']");
    private static final By SEND_EMAIL_BUTTON = By.xpath("//button[contains(@class, 'ComposeControlPanelButton-Button_action')]");

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

        driver.findElement(SEND_TO_LOCATOR).sendKeys(email.getRecipient());
        driver.findElement(MAIL_SUBJECT_LOCATOR).sendKeys(email.getSubject());
        driver.findElement(MAIL_BODY_LOCATOR).sendKeys(email.getBody());

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
        waitForVisibilityOf(mailSubjectNameLocator);

        actualEmail.setRecipient(driver.findElement(mailRecipientNameLocator).getText());
        actualEmail.setSubject(driver.findElement(mailSubjectNameLocator).getText());
        actualEmail.setBody(driver.findElement(mailBodyNameLocator).getText());

        return actualEmail;
    }


}
