package com.Pages;

import com.Utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    @FindBy(xpath="//a[text()='Log in']")
    private WebElement loginLink;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath="//input[@class=\"button-1 login-button\"]")
    private WebElement loginButton;

    @FindBy(linkText = "Forgot password?")
    private WebElement forgotPasswordLink;

    @FindBy(name="send-email")
    private WebElement recoverButton;

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void clickLoginLink() {
        loginLink.click();
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    public void clickRecoverButton() {
        recoverButton.click();
    }
}