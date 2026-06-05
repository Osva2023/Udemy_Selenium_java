package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropdownsEditboxesValidationsTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://rahulshettyacademy.com/angularpractice";
    private static final int TIMEOUT = 10;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        driver.get(URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldFillFormAndPrintSuccessMessage() {
        // Fill text fields
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        nameField.sendKeys("Osvaldo Test");

        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("osvaldo.selenium@test.com");

        WebElement passwordField = driver.findElement(By.id("exampleInputPassword1"));
        passwordField.sendKeys("StrongPassword123");

        // Check the Love IceCream checkbox
        WebElement loveIceCreamCheckbox = driver.findElement(By.id("exampleCheck1"));
        if (!loveIceCreamCheckbox.isSelected()) {
            loveIceCreamCheckbox.click();
        }
        Assertions.assertTrue(loveIceCreamCheckbox.isSelected());

        // Select gender from dropdown
        WebElement genderDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
        Select genderSelect = new Select(genderDropdown);
        genderSelect.selectByVisibleText("Male");
        Assertions.assertEquals("Male", genderSelect.getFirstSelectedOption().getText());

        // Select Student radio button
        WebElement studentRadio = driver.findElement(By.id("inlineRadio1"));
        if (!studentRadio.isSelected()) {
            studentRadio.click();
        }
        Assertions.assertTrue(studentRadio.isSelected());

        // Enter date in date picker
        WebElement datePicker = driver.findElement(By.name("bday"));
        datePicker.sendKeys("06/05/2026");

        // Submit form
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        // Capture and print success message
        WebElement successAlert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        String successText = successAlert.getText().trim();

        System.out.println("Success message: " + successText);

        Assertions.assertTrue(successText.contains("Success"));
    }
}
