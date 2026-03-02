package com.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckboxExercises {
    public static void main(String[] args) {
        // Code for handling checkboxes will go here.
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("checkBoxOption1")).click();
        driver.findElement(By.id("checkBoxOption1")).isSelected();
        Assert.assertTrue(driver.findElement(By.id("checkBoxOption1")).isSelected());
        System.out.println("Confirmed that checkbox is selected: " + driver.findElement(By.id("checkBoxOption1")).isSelected());
        driver.findElement(By.id("checkBoxOption1")).click();
        Assert.assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());
        System.out.println("Confirmed that checkbox is deselected: if false: " + driver.findElement(By.id("checkBoxOption1")).isSelected());
        
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type=\"checkbox\"]"));
        System.out.println("Total number of checkboxes: " + checkboxes.size());
        driver.quit();

    }
}
