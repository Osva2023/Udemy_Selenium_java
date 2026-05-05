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

public class HandleWebTablesTest {

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

    // Scenario 1: Print and assert the total number of rows in the courses table
    @Test
    public void shouldPrintNumberOfRows() {
        // Wait for the table to be visible
        WebElement table = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("product")));

        // Get all <tr> elements inside the table (includes header row + data rows)
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        System.out.println("Number of rows in the table: " + rows.size());

        // Table has 1 header row + 10 data rows = 11 total
        Assertions.assertEquals(11, rows.size());
    }

    // Scenario 2: Print and assert the number of columns in the courses table
    @Test
    public void shouldPrintNumberOfColumns() {
        // Wait for the table to be visible
        WebElement table = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("product")));

        // Get the header columns (<th> elements from the first row)
        List<WebElement> columns = table.findElements(By.tagName("th"));

        System.out.println("Number of columns in the table: " + columns.size());

        // Table has 3 columns: Instructor, Course, Price
        Assertions.assertEquals(3, columns.size());
    }

    // Scenario 3: Print the text content of the second data row
    @Test
    public void shouldPrintSecondDataRowContent() {
        // Wait for the table to be visible
        WebElement table = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("product")));

        // Get all rows; index 0 is the header, index 1 is the first data row, index 2
        // is the second data row
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        WebElement secondDataRow = rows.get(2);

        // Get each cell in the second data row
        List<WebElement> cells = secondDataRow.findElements(By.tagName("td"));

        System.out.println("Second data row content:");
        for (WebElement cell : cells) {
            System.out.println("  " + cell.getText());
        }

        // Assert the expected values for: Rahul Shetty | Learn SQL... | 25
        Assertions.assertEquals("Rahul Shetty", cells.get(0).getText());
        Assertions.assertEquals("Learn SQL in Practical + Database Testing from Scratch", cells.get(1).getText());
        Assertions.assertEquals("25", cells.get(2).getText());
    }
}
