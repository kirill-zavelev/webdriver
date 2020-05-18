package com.epam.webdriver.page;

import com.epam.webdriver.base.BasePage;
import com.epam.webdriver.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InboxPage extends BasePage {

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private WebElement emailPreview;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftFolderBtn;

    @FindBy(xpath = "//a[@href='#sent']")
    private WebElement sendFolderBtn;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public DraftPage openDraftsFolder() {
        waitForElementToBeClickable(draftFolderBtn);
        draftFolderBtn.click();

        return new DraftPage(driver);
    }

    public SendPage openSendFolder() {
        waitForElementToBeClickable(sendFolderBtn);
        sendFolderBtn.click();

        return new SendPage(driver);
    }
}