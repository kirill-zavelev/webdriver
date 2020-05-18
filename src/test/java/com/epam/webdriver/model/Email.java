package com.epam.webdriver.model;

import com.epam.webdriver.utils.PropertyLoader;
import com.github.javafaker.Faker;

import java.util.Objects;

public class Email {

    private String recipient;
    private String subject;
    private String body;

    public Email() {
        Faker faker = new Faker();
        this.recipient = PropertyLoader.loadProperty("user.send.to");
        this.subject = faker.commerce().material();
        this.body = faker.commerce().price();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(recipient, email.recipient) &&
                Objects.equals(subject, email.subject) &&
                Objects.equals(body, email.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, subject, body);
    }

    @Override
    public String toString() {
        return "Email{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
