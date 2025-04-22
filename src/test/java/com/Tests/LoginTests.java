package com.Tests;

import com.Pages.LoginPage;
import com.Utilities.DriverManager;
import com.Utilities.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    
    @Test
    public void testSuccessfulLogin() {
        try {
            LoginPage loginPage = new LoginPage();
            DriverManager.getDriver().get("https://demowebshop.tricentis.com");
            
            loginPage.clickLoginLink();
            LogManager.logInfo("Clicked on 'Login' link.");
            
            loginPage.enterEmail("abccy@gmail.com");
            loginPage.enterPassword("789456");
            LogManager.logInfo("Entered credentials");
            
            loginPage.clickLoginButton();
            LogManager.logInfo("Clicked login button");
            
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='abccy@gmail.com']")));
            
            Assert.assertEquals(emailElement.getText(), "abccy@gmail.com", "Login was not successful");
        } catch (Exception e) {
            LogManager.logError("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
    
    @Test
    public void testInvalidLogin() {
        try {
            LoginPage loginPage = new LoginPage();
            DriverManager.getDriver().get("https://demowebshop.tricentis.com");
            
            loginPage.clickLoginLink();
            loginPage.enterEmail("abccy@gmail.com");
            loginPage.enterPassword("wrongpass");
            loginPage.clickLoginButton();
            
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[text()='The credentials provided are incorrect']")));
            
            Assert.assertEquals(errorMessage.getText(), "The credentials provided are incorrect", 
                "Error message not displayed correctly");
        } catch (Exception e) {
            LogManager.logError("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
    
    @Test
    public void testForgotPassword() {
        try {
            LoginPage loginPage = new LoginPage();
            DriverManager.getDriver().get("https://demowebshop.tricentis.com");
            
            loginPage.clickLoginLink();
            loginPage.clickForgotPasswordLink();
            loginPage.enterEmail("valid@email.com");
            loginPage.clickRecoverButton();
            
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("result")));
            
            Assert.assertEquals(message.getText(), "Email with instructions has been sent to you.",
                "Password recovery message not displayed correctly");
        } catch (Exception e) {
            LogManager.logError("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}