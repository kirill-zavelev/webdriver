package com.epam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumYandexMailPage extends AbstractPage {

    private static final By MAIL_PAGE_LOCATOR = By.xpath("/html/body/div[4]/ul/ul/li[1]/a");
    private static final By NEW_MAIL_LOCATOR = By.className("mail-ComposeButton-Text");
    private static final By SEND_TO_LOCATOR = By.xpath("(//div[@class='composeYabbles'])[1]");
    private static final By MAIL_SUBJECT_LOCATOR = By.name("subject");
    private static final By MAIL_BODY_LOCATOR = By.xpath("//*[@id='cke_1_contents']/div");
    private static final By SAVE_MAIL_TO_DRAFT_LOCATOR = By.xpath("(//button[@class='controlButtons__btn controlButtons__btn--close'])[2]");
    private static final By MAIL_FOOTER = By.xpath("//a[@id='cke_37']");

    private static final By DRAFT_FOLDER_LOCATOR = By.xpath("//a[@data-title='Черновики']");
    private static final By MAIL_RECEPIENT_NAME_LOCATOR = By.xpath("(//span[@title='kiri4by@gmail.com'])[1]");
    private static final By MAIL_SUBJECT_NAME_LOCATOR = By.xpath("(//span[@title='Subject'])[1]");
    private static final By MAIL_BODY_NAME_LOCATOR = By.xpath("(//span[@title='Body'])[1]");

    public SeleniumYandexMailPage(WebDriver driver) {
        super(driver);
    }

    public SeleniumYandexMailPage openMailBox() {
        waitForElementToBeClickable(MAIL_PAGE_LOCATOR).click();

        return this;
    }

    public SeleniumYandexMailPage createNewMail(String recipient, String subject, String body) {
        waitForElementToBeClickable(NEW_MAIL_LOCATOR).click();
        waitForElementToBeClickable(MAIL_FOOTER);
        driver.findElement(SEND_TO_LOCATOR).sendKeys(recipient);
        driver.findElement(MAIL_SUBJECT_LOCATOR).sendKeys(subject);
        driver.findElement(MAIL_BODY_LOCATOR).sendKeys(body);

        return this;
    }

    public SeleniumYandexMailPage sendMailAsDraft() {
        driver.findElement(SAVE_MAIL_TO_DRAFT_LOCATOR).click();

        return this;
    }

//    public String verifyMailFormIsClosed(String expectedUser) {
//        By menuLocator = By.className(getUserMenuLocator(expectedUser));
//
//        return driver.findElement(menuLocator).getText();
//
//    }
//
//    private String getUserMenuLocator(String text) {
//        return "mail-User-Name" + text + "']";
//    }

    public SeleniumYandexMailPage openDraftsFolder() {
        driver.findElement(DRAFT_FOLDER_LOCATOR).click();

        return this;
    }

    public SeleniumYandexMailPage verifyDraftFolderContent() {
        driver.findElement(MAIL_RECEPIENT_NAME_LOCATOR);
        driver.findElement(MAIL_SUBJECT_NAME_LOCATOR);
        driver.findElement(MAIL_BODY_NAME_LOCATOR);

        return this;
    }


}
