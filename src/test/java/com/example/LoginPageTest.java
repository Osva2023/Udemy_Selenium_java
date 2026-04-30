package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * Automation test for Login Page Practice from Rahul Shetty Academy
 */
public class LoginPageTest {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://rahulshettyacademy.com/loginpagepractise";
    private static final int TIMEOUT = 10;
    
    @BeforeEach
    public void setUp() {
        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        
        // Navigate to the login page
        driver.get(URL);
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testLoginWithUserRoleAndAlert() {
        // Step 1: Fill username
        // TODO: Add username selector
        WebElement usernameField = driver.findElement(By.id("username")); // Placeholder
        usernameField.sendKeys("rahulshettyacademy");
        
        // Step 2: Fill password
        // TODO: Add password selector
        WebElement passwordField = driver.findElement(By.id("password")); // Placeholder
        passwordField.sendKeys("learning");
        
        // Step 3: Check the User checkbox
        // TODO: Add user checkbox selector
        WebElement userCheckbox = driver.findElement(By.id("chkboxOne")); // Placeholder
        userCheckbox.click();
        
        // Step 4: Handle alert pop-up that appears after checking User checkbox
        // TODO: Verify alert appears and click OK
        try {
            Alert alert = wait.until(d -> {
                try {
                    return d.switchTo().alert();
                } catch (Exception e) {
                    return null;
                }
            });
            if (alert != null) {
                System.out.println("Alert text: " + alert.getText());
                alert.accept(); // Click OK on alert
            }
        } catch (Exception e) {
            System.out.println("Alert handling: " + e.getMessage());
        }
        
        // Step 5: Select 'Consultant' from dropdown
        // TODO: Add dropdown selector and verify 'Consultant' option
        WebElement dropdown = driver.findElement(By.id("exampleFormControlSelect1")); // Placeholder
        Select select = new Select(dropdown);
        select.selectByValue("consultant"); // or selectByVisibleText("Consultant")
        
        // Step 6: Accept Terms and Conditions checkbox
        // TODO: Add terms checkbox selector
        WebElement termsCheckbox = driver.findElement(By.id("terms")); // Placeholder
        termsCheckbox.click();
        
        // Step 7: Click Submit button
        // TODO: Add submit button selector
        WebElement submitButton = driver.findElement(By.id("signInBtn")); // Placeholder
        submitButton.click();
        
        // Step 8: Wait for the next page to load
        // TODO: Add verification for the next page (title, URL, or specific element)
        wait.until(d -> d.getTitle().contains("success")); // Placeholder - adjust based on actual page
    }
}
