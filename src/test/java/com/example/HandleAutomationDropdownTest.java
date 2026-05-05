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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HandleAutomationDropdownTest {

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

    // Scenario: Type 3 letters in the auto-suggestive dropdown, select a country
    // with the mouse,
    // and verify the input box is updated with the selected country name
    @Test
    public void shouldSelectCountryFromAutoSuggestiveDropdown() {
        // Step 1: Locate the autocomplete input and type 3 letters to trigger
        // suggestions
        WebElement autocompleteInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("autocomplete")));
        autocompleteInput.sendKeys("Ita");

        // Step 2: Wait for the suggestion list to appear and collect all options
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".ui-autocomplete .ui-menu-item"), 0));

        System.out.println("Suggestions found: " + suggestions.size());
        suggestions.forEach(s -> System.out.println("  " + s.getText()));

        // Step 3: Click on "Italy" from the suggestion list (mouse selection)
        WebElement italyOption = suggestions.stream()
                .filter(s -> s.getText().equals("Italy"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Italy not found in suggestions"));
        italyOption.click();

        // Step 4: Verify the input box value was updated with the selected country
        String selectedValue = autocompleteInput.getAttribute("value");
        System.out.println("Selected country: " + selectedValue);

        Assertions.assertEquals("Italy", selectedValue);
    }
}
