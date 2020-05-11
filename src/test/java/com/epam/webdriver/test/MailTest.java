package com.epam.webdriver.test;

import com.epam.webdriver.base.BaseTest;
import com.epam.webdriver.model.Email;
import com.epam.webdriver.page.DraftsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailTest extends BaseTest {

    @Test
    public void loginTest() {

        String actualEmail = inboxPage
                .getActualEmail();

        //Verify that correct email is displayed after login
        Assert.assertEquals(actualEmail, EMAIL, "");
    }

    @Test
    public void sendEmailToDraftTest() {
        Email expectedEmail = new Email();

        inboxPage.openMailBox();

        mailCreationPage
                .createNewMail(expectedEmail)
                .sendMailAsDraft();

        Email actualEmail = inboxPage.openDraftsFolder()
                .getActualEmailFromList(expectedEmail);

        //Verify that recipient, subject and body are correct
        Assert.assertEquals(actualEmail, expectedEmail);
    }

    @Test
    public void deleteEmailFromDraft() {

        Email email = new Email();

        inboxPage.openMailBox();

        mailCreationPage.createNewMail(email)
                .sendMailAsDraft();

        inboxPage.openDraftsFolder()
                .checkEmailCheckbox(email);

        boolean isEmailDeleted = inboxPage.clickDeleteEmail()
                .isEmailDeleted(email);

        //Verify that email was deleted from list
        Assert.assertTrue(isEmailDeleted);
    }

    @Test
    public void updateEmailTest() {

        Email email = new Email();
        Email expectedEmailToBeUpdated = new Email();

        inboxPage.openMailBox();

        mailCreationPage.createNewMail(email)
                .sendMailAsDraft();

        inboxPage.openDraftsFolder();

        new DraftsPage(driver)
                .openEmail(email);

        mailCreationPage.fillEmail(expectedEmailToBeUpdated)
                .sendMail();

        Email actualEmailToBeUpdated = inboxPage.openSendFolder()
                .getActualEmailFromList(expectedEmailToBeUpdated);

        //Verify that email was updated
        Assert.assertEquals(actualEmailToBeUpdated, expectedEmailToBeUpdated);
    }

    @Test
    public void sendEmailTest() {
        Email expectedEmail = new Email();

        inboxPage.openMailBox();

        mailCreationPage.createNewMail(expectedEmail)
                .sendMail();

        Email actualEmail = inboxPage.openSendFolder()
                .getActualEmailFromList(expectedEmail);

        //Verify that email with correct fields was sent
        Assert.assertEquals(actualEmail, expectedEmail);
    }

    @Test
    public void logoutTest() {

        boolean isPasswordInputInteractable = loginPage
                .clickOnLogoutLink()
                .isPasswordInputDisplayed();

        //Verify that user was logged out
        Assert.assertTrue(isPasswordInputInteractable);
    }
}
