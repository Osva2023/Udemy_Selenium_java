package com.example;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {

  public static void main(String[] args) throws InterruptedException {
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    String password = getPassword(driver, wait);
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
    driver.findElement(By.linkText("Forgot your password?")).click();
    // <input type="text" placeholder="Name">
    // create xpath for the name input field
    Thread.sleep(1000); //

    driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");

    driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");

    driver.findElement(By.xpath("//input[@type='text'][2]")).clear();

    driver
        .findElement(By.cssSelector("input[type='text']:nth-child(3)"))
        .sendKeys("john@gmail.com");

    driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");

    driver.findElement(By.cssSelector(".reset-pwd-btn")).click();

    System.out.println(driver.findElement(By.cssSelector("form p")).getText());

    driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();

    Thread.sleep(1000);

    driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
    driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys(password);
    driver.findElement(By.id("chkboxOne")).click();
    driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
    Thread.sleep(1000);
    // check for succesfull login text:
    // <p style="color: rgb(27, 179, 102); font-size: 18px; text-align: center;">You are
    // successfully logged in.</p>
    String successText = driver.findElement(By.cssSelector("p")).getText();
    System.out.println(successText);
    Thread.sleep(3000);
    driver.findElement(By.className("logout-btn")).click();
    Thread.sleep(2000);
    String currentURL = driver.getCurrentUrl();
    System.out.println(currentURL);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("signInBtn")));
    System.out.println("Test completed successfully");
    driver.quit();
  }

  public static String getPassword(WebDriver driver, WebDriverWait wait)
      throws InterruptedException {
    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    driver.findElement(By.linkText("Forgot your password?")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
    String passwordText = driver.findElement(By.cssSelector("form p")).getText();

    // Please use temporary password 'rahulshettyacademy' to Login.
    String[] passwordArray = passwordText.split("'");
    String finalpasswordString = passwordArray[1].split("'")[0];
    return finalpasswordString;
  }
}
