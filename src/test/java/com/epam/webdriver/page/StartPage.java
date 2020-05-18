package com.epam.webdriver.page;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends AbstractPage {

    @FindBy(className = "user-account__name")
    private WebElement loggedUsername;

    protected StartPage(WebDriver driver) {
        super(driver);
    }

    public QuickActionsPanelPage clickOnUsername() {
        try {
            loggedUsername.click();
        } catch (StaleElementReferenceException sere) {
            driver.navigate().refresh();
            loggedUsername.click();
        }

        return new QuickActionsPanelPage(driver);
    }
}
