package com.epam.webdriver.test;

import com.epam.webdriver.base.BaseTest;
import com.epam.webdriver.model.Email;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailTest extends BaseTest {

    @Test
    public void loginTest() {

        String actualEmail = quickActionsPanelPage
                .getActualEmail();

        Assert.assertEquals(actualEmail, EMAIL, "Incorrect email is displayed.");
    }

    @Test
    public void sendEmailToDraftTest() {
        Email expectedEmail = new Email();

        quickActionsPanelPage.openMailBox();

        inboxPage.openMailCreationForm();

        mailCreationPage
                .fillEmail(expectedEmail)
                .sendMailAsDraft();

        Email actualEmail = inboxPage.openDraftsFolder()
                .getActualEmailFromList(expectedEmail);

        Assert.assertEquals(actualEmail, expectedEmail, "Incorrect email data. Please mail parameters.");
    }

    @Test
    public void deleteEmailFromDraft() {

        Email email = new Email();

        quickActionsPanelPage.openMailBox();

        inboxPage.openMailCreationForm();

        mailCreationPage.fillEmail(email)
                .sendMailAsDraft();

        inboxPage.openDraftsFolder()
                .checkEmailCheckbox(email);

        boolean isEmailDeleted = inboxPage
                .clickDeleteEmail()
                .isEmailDeleted(email);

        Assert.assertTrue(isEmailDeleted, "Email was not deleted from inbox.");
    }

    @Test
    public void updateEmailTest() {

        Email email = new Email();
        Email expectedEmailToBeUpdated = new Email();

        quickActionsPanelPage.openMailBox();

        inboxPage.openMailCreationForm();

        mailCreationPage.fillEmail(email)
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

        quickActionsPanelPage.openMailBox();

        inboxPage.openMailCreationForm();

        mailCreationPage.fillEmail(expectedEmail);

        Email actualEmail = inboxPage.openSendFolder()
                .getActualEmailFromList(expectedEmail);

        Assert.assertEquals(actualEmail, expectedEmail, "Expected email does not exist in the list.");
    }

//    @Test
//    public void logoutTest() {
//
//        boolean isPasswordInputInteractable = quickActionsPanelPage
//                .clickOnLogoutLink().isPasswordInputDisplayed();
//
//        Assert.assertTrue(isPasswordInputInteractable, "User was not logged out.");
//    }
}
