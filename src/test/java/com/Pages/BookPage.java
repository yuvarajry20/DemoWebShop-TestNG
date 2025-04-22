package com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BookPage extends BasePage {
    
    @FindBy(xpath="(//a[contains(text(),\"Books\")])[3]")
    public WebElement booksLink;
    
    @FindBy(xpath="//div[@class=\"product-grid\"]//div[@class=\"item-box\"]")
    public List<WebElement> booksList;
    
    @FindBy(xpath="//select[@id=\"products-orderby\"]")
    public WebElement sortByDropdown;
    
    @FindBy(xpath="//div[@class=\"product-grid\"]//div[@class=\"item-box\"]")
    public WebElement sortedBookList;
    
    @FindBy(xpath="//a[contains(text(),\"Fiction EX\")]")
    public WebElement fictionBook;
    
    @FindBy(xpath="//div[@class=\"overview\"]")
    public WebElement bookDetails;
    
    @FindBy(xpath="//select[@id=\"products-pagesize\"]")
    public WebElement displayDropdown;
    
    @FindBy(xpath="//select[@id=\"products-viewmode\"]")
    public WebElement viewAsDropdown;
    
    @FindBy(xpath = "//div[@class='side-2']//a[contains(@href, 'price')]")
    public List<WebElement> priceFilterLinks;
    
    @FindBy(xpath="//div[@class=\"product-list\"]")
    public WebElement filteredBooks;
    
    @FindBy(xpath="(//a[contains(text(),\"Computing and Internet\")])[2]")
    public WebElement computingInternetBook;
    
    @FindBy(xpath="//h2[@class=\"product-title\"]//a[contains(text(),\"Health Book\")]")
    public WebElement healthBook;
    
    @FindBy(xpath="//input[@value=\"Add to compare list\"]")
    public WebElement addToCompareButton;
    
    @FindBy(xpath="//table[@class='compare-products-table']//tr[2]/td")
    public List<WebElement> compareProductNames;
    
    @FindBy(xpath="//table[@class='compare-products-table']//tr[3]/td")
    public List<WebElement> compareProductPrices;
    
    @FindBy(xpath="//a[contains(text(),\"Clear list\")]")
    public WebElement clearListButton;
    
    @FindBy(xpath="//div[@class=\"page-body\"]")
    public WebElement emptyCompareListMessage;

    public BookPage() {
        PageFactory.initElements(driver, this);
    }
    
    public void selectSortOption(String option) {
        selectDropdownOption(sortByDropdown, option);
    }
    
    public void selectDisplayOption(String option) {
        selectDropdownOption(displayDropdown, option);
    }
    
    public void selectViewAsOption(String option) {
        selectDropdownOption(viewAsDropdown, option);
    }
    
    private void selectDropdownOption(WebElement dropdown, String option) {
        new Select(dropdown).selectByVisibleText(option);
    }
    
    public void applyPriceFilter(String priceRange) {
        for (WebElement filter : priceFilterLinks) {
            if (filter.getText().trim().equalsIgnoreCase(priceRange)) {
                filter.click();
                break;
            }
        }
    }
}