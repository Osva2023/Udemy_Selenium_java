package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HandleClickTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://rahulshettyacademy.com/AutomationPractice";
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
    public void shouldReuseCheckboxLabelAcrossDropdownEditboxAndAlert() {
        // Step 1: Select the second checkbox (Option2)
        List<WebElement> checkboxes = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("input[id^='checkBoxOption']"), 1));
        WebElement checkbox = checkboxes.get(1);
        checkbox.click();
        Assertions.assertTrue(checkbox.isSelected());

        // Step 2: Capture the label text from the selected checkbox
        String selectedLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@id='" + checkbox.getAttribute("id") + "']/parent::label")))
                .getText().trim();
        Assertions.assertFalse(selectedLabel.isEmpty());

        // Step 3: Select the same label in the dropdown
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("dropdown-class-example")));
        new Select(dropdown).selectByVisibleText(selectedLabel);

        // Step 4: Enter the same label in the edit box
        WebElement editBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        editBox.clear();
        editBox.sendKeys(selectedLabel);

        // Step 5: Click alert and verify it contains the selected label
        wait.until(ExpectedConditions.elementToBeClickable(By.id("alertbtn"))).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();

        Assertions.assertTrue(alertText.contains(selectedLabel));
        alert.accept();
    }
}
