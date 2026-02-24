package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class IntroSelenium {
  public static void main(String[] args) {
    // Invoking the browser will go here.
    // WebDriver driver = new ChromeDriver();
    // driver.get("https://rahulshettyacademy.com/");
    // try {
    //     Thread.sleep(5000); // Wait for 5 seconds to see the browser
    // }
    // catch (InterruptedException e) {
    //     e.printStackTrace();
    // }
    // String title = driver.getTitle();
    // System.out.println(title);
    // driver.quit();

    // Launch in firefox
    WebDriver driver = new FirefoxDriver();
    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    try {
      Thread.sleep(3000); // Wait for 5 seconds to see the browser
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String title = driver.getTitle();
    System.out.println(title);
    driver.quit();
  }
}
