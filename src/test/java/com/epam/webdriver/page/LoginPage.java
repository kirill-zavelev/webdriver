package com.epam.webdriver.page;

import org.openqa.selenium.StaleElementReferenceException;
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
    private WebElement passw;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitLogin;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitPassword;

    @FindBy(className = "passp-footer")
    private WebElement footer;

    @FindBy(xpath = "//a[text()='Выйти']")
    private List<WebElement> logout;

    @FindBy(className = "user-account__subname")
    private WebElement loggedUserEmail;

    @FindBy(className = "user-account__name")
    private WebElement loggedUserName;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage setUserName(String username) {
        userName.sendKeys(username);
        return this;
    }

    public LoginPage clickLogin() {
        wait.until(ExpectedConditions.visibilityOf(footer));
        submitLogin.click();

        return this;
    }

    public LoginPage setPassword(String password) {
        waitForElementToBeClickable(passw);
        passw.sendKeys(password);

        return this;
    }

    public LoginPage clickPassword() {
        submitPassword.click();

        return this;
    }

    public LoginPage clickOnUsername() {

        try {
            loggedUserName.click();
        } catch (StaleElementReferenceException sere) {
            driver.navigate().refresh();
            loggedUserName.click();
        }

        return this;
    }

    public String getActualEmail() {

        return loggedUserEmail.getText();
    }

    public LoginPage clickOnLogoutLink() {
        clickElementWhenItDisplayed(logout);

        return this;
    }

    public boolean isPasswordInputDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(passw)).isDisplayed();
    }
}
