package com.example.emailpasswordauthentication.Models;

public class Users {
    String UserName, EmailAddress, Password;

    public Users(String userName, String emailAddress, String password) {
        UserName = userName;
        EmailAddress = emailAddress;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPassword() {
        return Password;
    }

}