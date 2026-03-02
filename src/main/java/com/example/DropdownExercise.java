package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DropdownExercise {
    public static void main(String[] args) {
        //initialize WebDriver and navigate to the page
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        
        // fill text inputs
        driver.findElement(By.xpath("//div[@class='form-group']//input[@name='name']")).sendKeys("Gloria");
        driver.findElement(By.xpath("//div[@class='form-group']//input[@name='email']")).sendKeys("gloriatest@gmail.com");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("123456");

        //toogle the checkbox and verify it's selected
        driver.findElement(By.className("form-check-label")).click();
        System.out.println("Checkbox selected: " + driver.findElement(By.className("form-check-input")).isSelected());
        Assert.assertTrue(
            driver.findElement(By.className("form-check-input")).isSelected()) ;

        
        //select gender from dropdown and verify the selection
        WebElement genderDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
        Select genderSelect = new Select(genderDropdown);
        genderSelect.selectByVisibleText("Male");
        Assert.assertEquals(
            genderSelect.getFirstSelectedOption().getText(), "Male");
        
        //sleect student radio button and verify it's selected and has correct value and label
        driver.findElement(By.id("inlineRadio1")).click();
        Assert.assertTrue(
            driver.findElement(By.id("inlineRadio1")).isSelected());
            System.out.println("Radio button selected: " + driver.findElement(By.id("inlineRadio1")).isSelected());
        Assert.assertEquals(
            driver.findElement(By.id("inlineRadio1")).getDomAttribute("value"), "option1");
        
        WebElement studentLabel = driver.findElement(By.cssSelector("label[for='inlineRadio1']"));
        Assert.assertEquals(
            studentLabel.getText(), "Student");

        //enter date of birth
        driver.findElement(By.name("bday")).sendKeys("11/08/1984");

        //submit the form and verify success message
        driver.findElement(By.className("btn-success")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(driver1 -> driver1.findElement(By.className("alert-success")).isDisplayed());
        String successMessage = driver.findElement(By.className("alert-success")).getText();
        Assert.assertTrue(
            successMessage.contains("successfully"));
            System.out.println("Success message: " + successMessage);
        
        //close the browser
        driver.close();
    }
}
