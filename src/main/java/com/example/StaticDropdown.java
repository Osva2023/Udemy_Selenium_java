package com.example;

import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StaticDropdown {

    public static void main(String[] args) {
        // Code for handling static dropdown will go here.
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        // id is ctl00_mainContent_DropDownListCurrency

        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(3);
        System.out.println(dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("INR");
        System.out.println(dropdown.getFirstSelectedOption().getText());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("divpaxinfo")).click();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

        for (int i = 1; i < 5; i++)

        {

            driver.findElement(By.id("hrefIncAdt")).click();

        }

        driver.findElement(By.id("btnclosepaxoption")).click();

        String actualText = driver.findElement(By.id("divpaxinfo")).getText();
        if (!actualText.equals("5 Adult")) {
            throw new AssertionError("Expected '5 Adult' but got '" + actualText + "'");
        }

        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

        //driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
        driver.quit();
    }
}
