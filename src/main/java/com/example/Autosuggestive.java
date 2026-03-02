package com.example;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Autosuggestive {

  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    try {
      driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
      driver.findElement(By.id("autosuggest")).sendKeys("ind");

      wait.until(
          ExpectedConditions.visibilityOfElementLocated(
              By.cssSelector("li.ui-menu-item a")));

      List<WebElement> options = driver.findElements(By.cssSelector("li.ui-menu-item a"));

      for (WebElement option : options) {
        if ("India".equalsIgnoreCase(option.getText().trim())) {
          option.click();
          break;
        }
      }
    } finally {
      driver.quit();
    }
  }
}