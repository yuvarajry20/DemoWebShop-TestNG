package com.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;
    
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();
            
            switch (browser) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            
            // Common driver configurations
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}