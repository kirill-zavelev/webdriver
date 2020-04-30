package com.epam.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends AbstractPage {

//    private static final By MAIL_PAGE = By.xpath("//a[text()='Почта']");
//    private static final By DELETE_EMAIL = By.xpath("//span[contains(@class, 'item-title-delete')]");
//    private static final By EMAIL_PREVIEW = By.xpath("//div[@data-key='box=messages-item-box']");
//    private static final By EMAIL_CHECK_MARK = By.cssSelector("label rect");
//    private static final By DRAFT_FOLDER = By.xpath("//a[@href='#draft']");
//    private static final By SEND_FOLDER = By.xpath("//a[@href='#sent']");

    @FindBy(xpath = "//a[text()='Почта']")
    private WebElement mailPage;

    @FindBy(xpath = "//span[contains(@class, 'item-title-delete')]")
    private WebElement deleteEmail;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private WebElement emailPreview;

    @FindBy(css = "label rect")
    private WebElement emailCheckMark;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftFolder;

    @FindBy(xpath = "//a[@href='#sent']")
    private WebElement sendFolder;

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

//    public InboxPage checkInEmail(Email email) {
//        WebElement checkMark = findEmailPreview(EMAIL_PREVIEW, email).findElement(emailCheckMark);
//        waitForElementToBeClickable(emailCheckMark);
//        checkMark.click();
//
//        return this;
//    }
//
//    public InboxPage clickDeleteEmail() {
//        waitForElementToBeClickable(deleteEmail);
//        deleteEmail.click();
//
//        return this;
//    }
//
//    public InboxPage openEmail(Email email) {
//        waitForElementToBeClickable(draftFolder);
//        findEmailPreview(EMAIL_PREVIEW, email).click();
//
//        return this;
//    }
}
