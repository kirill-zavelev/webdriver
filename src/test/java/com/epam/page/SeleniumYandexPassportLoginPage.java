package com.epam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumYandexPassportLoginPage extends AbstractPage {

    private static final By USER_NAME_LOCATOR = By.id("passp-field-login");
    private static final By PASSWORD_LOCATOR = By.id("passp-field-passwd");
    private static final By SUBMIT_LOGIN_LOCATOR = By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[3]/div[2]/div/div/div[1]/form/div[3]/button[1]");
    private static final By SUBMIT_PASSWORD_LOCATOR = By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[3]/div[2]/div/div/form/div[2]/button[1]");
    private static final By FOOTER_LOCATOR = By.xpath("//*[@id='root']/div/div/footer");

    public SeleniumYandexPassportLoginPage(WebDriver driver) {
        super(driver);
    }

    public SeleniumYandexPassportLoginPage typeUserName(String username) {
        driver.findElement(USER_NAME_LOCATOR).sendKeys(username);

        return this;
    }

    public SeleniumYandexPassportLoginPage submitLogin() {
        waitForVisibilityOf(FOOTER_LOCATOR);
        driver.findElement(SUBMIT_LOGIN_LOCATOR).click();

        return this;
    }

    public SeleniumYandexPassportLoginPage typePassword(String password) {
        waitForElementToBeClickable(PASSWORD_LOCATOR);
        driver.findElement(PASSWORD_LOCATOR).sendKeys(password);

        return this;
    }

    public SeleniumYandexPassportLoginPage submitPassword() {
        driver.findElement(SUBMIT_PASSWORD_LOCATOR).click();

        return this;
    }

    public SeleniumYandexPassportLoginPage clickOnUsername(String username) {
        By usernameLocator = By.xpath(getBaseSpanTextLocator(username));
        waitForElementToBeClickable(usernameLocator);
        driver.findElement(usernameLocator).click();

        return this;
    }

    public String getActualEmail(String expectedEmail) {
        By emailLocator = By.xpath(getBaseSpanTextLocator(expectedEmail));

        return driver.findElement(emailLocator).getText();
    }

    private String getBaseSpanTextLocator(String text) {
        return "//span[text()='" + text + "']";
    }
}
