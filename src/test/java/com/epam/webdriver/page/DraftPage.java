package com.epam.webdriver.page;

import com.epam.webdriver.base.BasePage;
import com.epam.webdriver.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DraftPage extends BasePage {

    @FindBy(xpath = "//*[@data-id='2']")
    private WebElement deleteEmailBtn;

    @FindBy(xpath = "//div[@data-key='box=messages-item-box']")
    private List<WebElement> emailPreviews;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftFolderBtn;

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    public DraftPage openEmail(Email email) {
        waitForElementToBeClickable(draftFolderBtn);
        findEmailPreview(emailPreviews, email).click();

        return this;
    }

    public DraftPage checkEmailCheckbox(Email email) {
        By emailCheckMark = By.cssSelector("label rect");
        WebElement checkMark = findEmailPreview(emailPreviews, email).findElement(emailCheckMark);
        waitForElementToBeClickable(checkMark);
        callContextMenu(checkMark);

        return this;
    }

    public DraftPage clickDeleteEmail() {
        waitForElementToBeClickable(deleteEmailBtn);
        deleteEmailBtn.click();

        return this;
    }

    public boolean isEmailDeleted(Email email) {
        return emailPreviews
                .stream()
                .noneMatch(emailPreview -> !emailPreview.getText().contains(email.getSubject())
                && !emailPreview.getText().contains(email.getBody())
                && !emailPreview.getText().contains(email.getRecipient()));
    }
}
