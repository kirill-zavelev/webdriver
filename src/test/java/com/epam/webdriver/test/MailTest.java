package com.epam.webdriver.test;

import com.epam.webdriver.base.BaseTest;
import com.epam.webdriver.model.Email;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailTest extends BaseTest {

    @Test
    public void loginTest() {

        String actualEmail = inboxPage
                .getActualEmail();

        Assert.assertEquals(actualEmail, EMAIL, "Incorrect email is displayed.");
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

        Assert.assertEquals(actualEmail, expectedEmail, "Incorrect email data. Please mail parameters.");
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

        Assert.assertTrue(isEmailDeleted, "Email was not deleted from inbox.");
    }

    @Test
    public void updateEmailTest() {

        Email email = new Email();
        Email expectedEmailToBeUpdated = new Email();

        inboxPage.openMailBox();

        mailCreationPage.createNewMail(email)
                .sendMailAsDraft();

        inboxPage.openDraftsFolder()
                .openEmail(email);

        mailCreationPage.fillEmail(expectedEmailToBeUpdated)
                .sendMail();

        Email actualEmailToBeUpdated = inboxPage.openSendFolder()
                .getActualEmailFromList(expectedEmailToBeUpdated);

        Assert.assertEquals(actualEmailToBeUpdated, expectedEmailToBeUpdated, "Email was not updated.");
    }

    @Test
    public void sendEmailTest() {
        Email expectedEmail = new Email();

        inboxPage.openMailBox();

        mailCreationPage.createNewMail(expectedEmail)
                .sendMail();

        Email actualEmail = inboxPage.openSendFolder()
                .getActualEmailFromList(expectedEmail);

        Assert.assertEquals(actualEmail, expectedEmail, "Expected email does not exist in the list.");
    }

    @Test
    public void logoutTest() {

        boolean isPasswordInputInteractable = loginPage
                .clickOnLogoutLink()
                .isPasswordInputDisplayed();

        Assert.assertTrue(isPasswordInputInteractable, "User was not logged out.");
    }
}
