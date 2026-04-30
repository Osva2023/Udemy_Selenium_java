package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

/**
 * Automation test for Login Page Practice from Rahul Shetty Academy
 */
public class LoginPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://rahulshettyacademy.com/loginpagepractise";
    private static final String SHOP_URL_FRAGMENT = "/angularpractice/shop";
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
    public void testLoginWithUserRoleAndModal() {
        // Step 1: Fill username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.sendKeys("rahulshettyacademy");

        // Step 2: Fill password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Learning@830$3mK2");

        // Step 3: Select the User radio option
        WebElement userRadioLabel = driver
                .findElement(By.xpath("//label[contains(@class,'customradio')][.//input[@id='usertype']]"));
        userRadioLabel.click();

        // Step 4: Accept the confirmation modal for User if it appears
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement okButton = shortWait.until(ExpectedConditions.elementToBeClickable(By.id("okayBtn")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okButton);
        } catch (TimeoutException ignored) {
            // Some runs don't show the modal; continue test flow.
        }

        // Step 5: Select 'Consultant' from dropdown
        WebElement dropdown = driver.findElement(By.cssSelector("select.form-control"));
        Select select = new Select(dropdown);
        select.selectByValue("consult");

        // Step 6: Accept Terms and Conditions checkbox
        WebElement termsCheckbox = driver.findElement(By.id("terms"));
        termsCheckbox.click();

        // Step 7: Click Submit button
        WebElement submitButton = driver.findElement(By.id("signInBtn"));
        submitButton.click();

        // Step 8: Wait for the next page to load
        wait.until(ExpectedConditions.urlContains(SHOP_URL_FRAGMENT));
        Assertions.assertTrue(driver.getCurrentUrl().contains(SHOP_URL_FRAGMENT));

        // Step 9: Add all available items to the cart dynamically
        By addButtonSelector = By.cssSelector("app-card .card-footer .btn.btn-info");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(addButtonSelector, 0));
        List<WebElement> addButtons = driver.findElements(addButtonSelector);

        int itemsToAdd = addButtons.size();
        for (WebElement addButton : addButtons) {
            addButton.click();
        }

        // Step 10: Assert checkout counter matches selected item count
        By checkoutButtonSelector = By.xpath("//a[contains(@class,'nav-link') and contains(.,'Checkout')]");
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(checkoutButtonSelector, String.valueOf(itemsToAdd)));

        String checkoutText = driver.findElement(checkoutButtonSelector).getText();
        int checkoutCount = Integer.parseInt(checkoutText.replaceAll("[^0-9]", ""));
        Assertions.assertEquals(itemsToAdd, checkoutCount);

        // Step 11: Click Checkout and wait for checkout view to load
        driver.findElement(checkoutButtonSelector).click();
        By checkoutTableSelector = By.cssSelector("table.table.table-hover");
        WebElement checkoutTable = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTableSelector));
        Assertions.assertTrue(checkoutTable.isDisplayed());
    }
}
