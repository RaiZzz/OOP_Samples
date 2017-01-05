package com.crowdrise.oldtests;

/**
 * Created by MadMax on 1/4/2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestSample_01 {

    WebDriver driver = new ChromeDriver();
    public String baseUrl = "http://sample.com"; //Sample, not exist
    public String firstName = "Sania";           //First Name for new employee
    public String lastName = "Mestnii";          //Last Name for new employee
    public String status = "Full Time";          //Status

    @BeforeTest
    public void setUp() {

        //Open browser on the main page and login
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys("sweta");
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys("november");
        driver.findElement(By.id("btnLogin")).click();
    }

    @Test
    public void verifyNewEmployeeCreation()
    {
        //Verify new title
        Assert.assertEquals(driver.getTitle(), "???");
        //Navigate to employee creation page
        driver.findElement(By.id("btnAdd")).click();
        //Save employee ID in new variable
        String idName = driver.findElement(By.id("employeeId")).getAttribute("value");
        //Fill out all required fields and save
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("btnSave")).click();
        driver.findElement(By.linkText("Job")).click();
        driver.findElement(By.id("btnSave")).click();
        //Change status to value of "status" variable and save
        Select dropMenu = new Select(driver.findElement(By.id("job_emp_status")));
        dropMenu.selectByVisibleText(status);
        driver.findElement(By.id("btnSave")).click();
        //Navigate to search page
        driver.navigate().to("http://...");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Search by full name
        driver.findElement(By.id("empsearch_employee_name_empName")).click();
        driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys(firstName + " " + lastName);
        //(For search)If it is not only one person with that full name, will choose first
        if (driver.findElement(By.xpath("html/body/div[4]/ul/li[1]")).isEnabled())
            driver.findElement(By.xpath("html/body/div[4]/ul/li[1]")).click();
        //Click button search and looking for out new created employee
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.xpath("//*[@id='resultTable']/thead/tr/th[2]/a")).click();
        driver.findElement(By.xpath("//*[@id='resultTable']/thead/tr/th[2]/a")).click();
        //Verify that new employee with out credentials is created.
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText(), idName);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[6]")).getText(), status);
        //Displayed ID, Full name and status in console.
        System.out.println("User information: " + idName + " " + firstName + " " + lastName + " " + status);
    }

    @AfterTest
    public void breakDown() throws InterruptedException {
        //Log Out and close browser.
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(500);
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }
}

