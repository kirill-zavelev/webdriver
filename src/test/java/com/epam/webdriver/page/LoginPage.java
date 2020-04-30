package com.epam.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends AbstractPage {

//    private static final By USER_NAME = By.id("passp-field-login");
//    private static final By PASSWORD = By.id("passp-field-passwd");
//    private static final By SUBMIT_LOGIN = By.xpath("//button[@type='submit']");
//    private static final By SUBMIT_PASSWORD = By.xpath("//button[@type='submit']");
//    private static final By FOOTER = By.className("passp-footer");
//    private static final By LOGOUT = By.xpath("//a[text()='Выйти']");
//    private static final By LOGGED_USER_EMAIL = By.xpath("//span[@class='user-account__subname']");

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

    @FindBy(xpath = "//span[@class='user-account__subname']")
    private WebElement loggedUserEmail;

//    public LoginPage(WebDriver driver) {
//        super(driver);
//    }

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage setUserName(String username) {
//        driver.findElement(USER_NAME).sendKeys(username);
        userName.sendKeys(username);
        return this;
    }

    public LoginPage clickLogin() {
//        waitForVisibilityOf(FOOTER);
        wait.until(ExpectedConditions.visibilityOf(footer));
        submitLogin.click();

        return this;
    }

    public LoginPage setPassword(String password) {
        waitForElementToBeClickable(passw);
        passw.sendKeys(password);
//        driver.findElement(PASSWORD).sendKeys(password);

        return this;
    }

    public LoginPage clickPassword() {
//        driver.findElement(SUBMIT_PASSWORD).click();
        submitPassword.click();

        return this;
    }

    public LoginPage clickOnUsername(String username) {
        WebElement usernameLocator = getBaseSpanTextLocator(username);
        waitForElementToBeClickable(usernameLocator);
        usernameLocator.click();

        return this;
    }

    public String getActualEmail(String expectedEmail) {

//        return driver.findElement(LOGGED_USER_EMAIL).getText();
        return loggedUserEmail.getText();
    }

    public LoginPage clickOnLogoutLink() {
        clickElementWhenItDisplayed(logout);

        return this;
    }

    public boolean isPasswordInputDisplayed() {
        waitForVisibilityOf(passw);

        return passw.isDisplayed();
    }
}
