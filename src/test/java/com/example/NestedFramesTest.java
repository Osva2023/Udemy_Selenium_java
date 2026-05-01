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

public class NestedFramesTest {

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
    public void shouldPrintMiddleFrameText() {
        // Step 1: Open the Nested Frames page
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Nested Frames"))).click();

        // Step 2: Switch to top frame and then to middle frame
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame-top"));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame-middle"));

        // Step 3: Capture and print the middle frame text
        String middleText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("content"))).getText();
        System.out.println("Middle frame text: " + middleText);

        // Step 4: Validate the extracted value
        Assertions.assertEquals("MIDDLE", middleText);
    }
}
