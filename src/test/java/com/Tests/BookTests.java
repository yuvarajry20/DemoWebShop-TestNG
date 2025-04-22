package com.Tests;

import com.Pages.BookPage;
import com.Utilities.ConfigReader;
import com.Utilities.DriverManager;
import com.Utilities.LogManager;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BookTests extends BaseTest {
    private BookPage bookPage;

    @BeforeMethod
    public void setUp() {
        bookPage = new BookPage();
        DriverManager.getDriver().get("https://demowebshop.tricentis.com");
    }

    @Test(priority = 1)
    public void testBookCategoryNavigation() {
        bookPage.booksLink.click();
        LogManager.logInfo("Clicked on Books category");
        
        Assert.assertTrue(bookPage.booksList.size() > 0, 
            "Book list should be displayed");
        LogManager.logInfo("Number of books displayed: " + bookPage.booksList.size());
    }

    @Test(priority = 2)
    public void testBookDetailsPage() {
        bookPage.booksLink.click();
        bookPage.fictionBook.click();
        LogManager.logInfo("Clicked on Fiction book");
        
        Assert.assertTrue(bookPage.bookDetails.isDisplayed(), 
            "Book details should be displayed");
        LogManager.logInfo("Book description: " + bookPage.bookDetails.getText());
    }

    @Test(priority = 3)
    public void testSortingOptions() {
        bookPage.booksLink.click();
        
        String[] sortOptions = {
            "Name: A to Z",
            "Name: Z to A",
            "Price: Low to High",
            "Price: High to Low",
            "Created on"
        };
        
        for (String option : sortOptions) {
            bookPage.selectSortOption(option);
            LogManager.logInfo("Applied sort option: " + option);
            
            String selectedOption = new Select(bookPage.sortByDropdown)
                .getFirstSelectedOption().getText();
            Assert.assertEquals(selectedOption, option, 
                "Sort option should be applied correctly");
        }
    }

    @Test(priority = 4)
    public void testPriceFilter() {
        bookPage.booksLink.click();
        bookPage.applyPriceFilter("Under $25.00");
        LogManager.logInfo("Applied price filter: Under $25.00");
        
        Assert.assertTrue(bookPage.filteredBooks.isDisplayed(), 
            "Filtered books should be displayed");
    }

    @Test(priority = 5)
    public void testCompareProducts() {
        bookPage.booksLink.click();
        
        
        bookPage.computingInternetBook.click();
        bookPage.addToCompareButton.click();
        LogManager.logInfo("Added Computing and Internet book to compare");
        
        
        DriverManager.getDriver().navigate().back();
        bookPage.healthBook.click();
        bookPage.addToCompareButton.click();
        LogManager.logInfo("Added Health book to compare");
        
        
        Assert.assertTrue(bookPage.compareProductNames.size() >= 2, 
            "Should have at least 2 products in comparison");
        
        
        bookPage.clearListButton.click();
        Assert.assertTrue(bookPage.emptyCompareListMessage.getText()
            .contains("You have no items to compare"), 
            "Compare list should be empty");
    }
}