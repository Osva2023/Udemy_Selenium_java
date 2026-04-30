package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowsHandleAssignmentTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://the-internet.herokuapp.com";
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
    public void shouldCaptureTextFromBothWindows() {
        // Step 1: Open the Multiple Windows page
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Multiple Windows"))).click();

        // Step 2: Save the parent window id
        String parentWindow = driver.getWindowHandle();

        // Step 3: Click on Click Here to open the new window
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Click Here"))).click();

        // Step 4: Wait until both windows are available
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String firstWindow = it.next();
        String secondWindow = it.next();

        String childWindow = firstWindow.equals(parentWindow) ? secondWindow : firstWindow;

        // Step 5: Switch to the child window and capture the text
        driver.switchTo().window(childWindow);
        String childWindowMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h3"))).getText();
        System.out.println("New window text: " + childWindowMessage);
        Assertions.assertEquals("New Window", childWindowMessage);

        // Step 6: Switch back to the parent window and capture the text
        driver.switchTo().window(parentWindow);
        String parentWindowMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h3"))).getText();
        System.out.println("First window text: " + parentWindowMessage);
        Assertions.assertEquals("Opening a new window", parentWindowMessage);
    }
}