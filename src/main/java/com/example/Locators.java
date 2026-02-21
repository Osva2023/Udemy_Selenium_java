package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys("Osvaldo");
        // driver.findElement(By.name("inputPassword")).sendKeys("rahulshettyacademy");
        // using wrong password to see the error message
        driver.findElement(By.name("inputPassword")).sendKeys("password123");

        driver.findElement(By.className("signInBtn")).click();
        // <p class="error">* Incorrect username or password </p>
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
        System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout-btn"))).click();

    }
}
