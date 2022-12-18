package com.hepsiburada.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By loginBtn = By.id("btnLogin");
    By emailTxt = By.id("txtUserName");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginBtnDisplayed() {
        return isElementDisplayed(loginBtn);
    }

    public boolean isEmailTxtDisplayed() {
        return isElementDisplayed(emailTxt);
    }
}
