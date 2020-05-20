package com.epam.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class QuickActionsPanelPage extends AbstractPage {

    @FindBy(className = "user-account__subname")
    private WebElement loggedUserEmail;

    @FindBy(xpath = "//a[@href='https://mail.yandex.ru']")
    private WebElement mailPage;

    @FindBy(xpath = "//a[text()='Выйти']")
    private List<WebElement> logoutBtn;

    public QuickActionsPanelPage(WebDriver driver) {
        super(driver);
    }

    public String getActualEmail() {

        return loggedUserEmail.getText();
    }

    public InboxPage openMailBox() {
        waitForElementToBeClickable(mailPage).click();

        return new InboxPage(driver);
    }

    public QuickActionsPanelPage clickOnLogoutLink() {
        clickElementWhenItDisplayed(logoutBtn);

        return this;
    }
}
