package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DinamicDropdown {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Code for handling dynamic dropdown will go here.
        //// a[@value='MAA'] dinamic for chennai in FROM
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[@value='MAA'])[2]"))).click();
        String actualText = driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).getText();
        if (actualText.equals("Chennai (MAA)")) {
            System.out.println("Test passed: " + actualText);
        } else {
            System.out.println("Test failed: Expected 'Chennai (MAA)', but got '" + actualText + "'");
        }
        driver.quit();
    }
}
