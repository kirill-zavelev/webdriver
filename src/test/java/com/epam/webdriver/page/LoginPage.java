package com.epam.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends AbstractPage {

    @FindBy(id = "passp-field-login")
    private WebElement userName;

    @FindBy(id = "passp-field-passwd")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitLoginBtn;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitPasswordBtn;

    @FindBy(className = "passp-footer")
    private WebElement footer;

    @FindBy(xpath = "//a[text()='Выйти']")
    private List<WebElement> logoutBtn;

    @FindBy(className = "user-account__name")
    private WebElement loggedUserName;

    public LoginPage(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this);
    }

    private LoginPage setUserName(String username) {
        userName.sendKeys(username);
        highLightText(userName);
        return this;
    }

    public InboxPage login(String username, String password) {
        setUserName(username);
        clickLogin();
        setPassword(password);
        clickPassword();

        return new InboxPage(driver);
    }

    private LoginPage clickLogin() {
        wait.until(ExpectedConditions.visibilityOf(footer));
        submitLoginBtn.click();

        return this;
    }

    private LoginPage setPassword(String password) {
        waitForElementToBeClickable(this.password);
        this.password.sendKeys(password);

        return this;
    }

    private LoginPage clickPassword() {
        submitPasswordBtn.click();

        return this;
    }

    public LoginPage clickOnLogoutLink() {
        clickElementWhenItDisplayed(logoutBtn);

        return this;
    }

    public boolean isPasswordInputDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(password)).isDisplayed();
    }
}
