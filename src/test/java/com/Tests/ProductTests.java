package com.Tests;

import com.Pages.ProductPage;
import com.Utilities.DriverManager;
import com.Utilities.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTests extends BaseTest {
    private ProductPage productPage;

    @Test(priority = 1)
    public void testProductSearchAndAddToCart() {
        productPage = new ProductPage();
        DriverManager.getDriver().get("https://demowebshop.tricentis.com");
        
        
        productPage.SearchStoreField.sendKeys("laptop" + Keys.ENTER);
        LogManager.logInfo("Searched for product: laptop");
        
        
        String title = productPage.ProductTitle.getText();
        LogManager.logInfo("Product title: " + title);
        Assert.assertNotNull(title, "Product title should not be null");
        
        
        productPage.AddToCart.click();
        LogManager.logInfo("Added product to cart");
        
        
        String cartCount = productPage.AddTocartnumber.getText();
        Assert.assertTrue(Integer.parseInt(cartCount.replaceAll("[^0-9]", "")) > 0, 
            "Cart count should increase after adding product");
    }

    @Test(priority = 2)
    public void testWishlistFunctionality() {
        productPage = new ProductPage();
        DriverManager.getDriver().get("https://demowebshop.tricentis.com");
        
        
        productPage.imgalbum3.click();
        productPage.addtowishlist.click();
        productPage.clickwishlist.click();
        LogManager.logInfo("Added product to wishlist");
        
        
        String wishlistUrl = productPage.urlforwishlist.getText();
        Assert.assertNotNull(wishlistUrl, "Wishlist URL should be generated");
        
        
        String productName = productPage.productnameinwishlist.getText();
        Assert.assertEquals(productName, "3rd Album", 
            "Product name in wishlist should match");
    }

    @Test(priority = 3)
    public void testEmailAFriend() {
        productPage = new ProductPage();
        DriverManager.getDriver().get("https://demowebshop.tricentis.com");
        
        
        productPage.LoginButtonHomePage.click();
        productPage.EmailField.sendKeys("abcggggg123@Gmail.com");
        productPage.PasswordField.sendKeys("Divraj@1234");
        productPage.LoginButtonSubmit.click();
        LogManager.logInfo("Logged in successfully");
        
        
        productPage.giftcardpage.click();
        productPage.emailfriendbtn.click();
        
        
        productPage.friendemail.sendKeys("friend@example.com");
        productPage.myemail.clear();
        productPage.myemail.sendKeys("abcggggg123@Gmail.com");
        productPage.sendmail.click();
        LogManager.logInfo("Sent email to friend");
        
        
        String successMessage = productPage.successfulsentmail.getText();
        Assert.assertTrue(successMessage.contains("Your message has been sent"),
            "Email should be sent successfully");
    }

    @Test(priority = 4)
    public void testCartManagement() {
        productPage = new ProductPage();
        DriverManager.getDriver().get("https://demowebshop.tricentis.com");
        
        
        productPage.SearchStoreField.sendKeys("laptop" + Keys.ENTER);
        productPage.AddToCart.click();
        
        
        productPage.shoppingcart.click();
        
        
        String productInCart = productPage.cartproductavailable.getText();
        Assert.assertNotNull(productInCart, "Product should be in cart");
        
        
        productPage.removefromcart.click();
        productPage.updatecart.click();
        
        
        String emptyCartMessage = productPage.emptycart.getText();
        Assert.assertTrue(emptyCartMessage.contains("Your Shopping Cart is empty"),
            "Cart should be empty after removal");
    }

    @Test(priority = 5)
    public void testShippingEstimation() {
        productPage = new ProductPage();
        DriverManager.getDriver().get("https://demowebshop.tricentis.com");
        
        
        productPage.SearchStoreField.sendKeys("laptop" + Keys.ENTER);
        productPage.AddToCart.click();
        
        
        productPage.shoppingcart.click();
        
        
        Select countryDropdown = new Select(productPage.countryclick);
        countryDropdown.selectByVisibleText("United States");
        productPage.zipcode.sendKeys("10001");
        productPage.shippingestimate.click();
        
        
        WebElement shippingOptions = DriverManager.getDriver()
            .findElement(By.xpath("//ul[@class=\"shipping-results\"]"));
        Assert.assertTrue(shippingOptions.isDisplayed(), 
            "Shipping options should be displayed");
    }
}