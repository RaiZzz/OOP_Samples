package com.crowdrise.oldtests;

/**
 * Created by MadMax on 1/4/2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSample_02
{
    WebDriver driver = new ChromeDriver();

    public String Url = "http://sample.com";       // Sample, not exist
    public String login = "raizzz.test@gmail.com"; // Login
    public String password = "012345";             // Password
    public String firstName = "Petia";             // First name in report
    public String lastName = "Sumkin";             // Last name in report
    public String stopLocation = "115 Hell Ave";   // Stop location address
    public int role = 1;                           // 0 - Pedestrian, 1 - Passenger, 2 - Driver

    @BeforeTest
    public void setUp() throws InterruptedException
    {
        //Open URL, login on site
        driver.get(Url);
        driver.manage().window().maximize();
        Thread.sleep(1500);
        WebElement element = driver.findElement(By.id("exampleInputEmail1"));
        element.clear();
        element.sendKeys(login);
        element = driver.findElement(By.name("account_password"));
        element.clear();
        element.sendKeys(password);
        driver.findElement(By.tagName("button")).submit();
    }

    @Test
    public void addReport() throws InterruptedException
    {
        // Open "Add Report", choose first organization in list
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.linkText("Add Report")).click();
        List<WebElement> dep = driver.findElements(By.xpath("//input[@ng-model='wizard.report.key']"));
        dep.get(0).click();
        // Create List with all menu items
        List<WebElement> mainMenu = driver.findElements(By.xpath("//ol[@class='row']/li"));
        mainMenu.get(1).click();
        // Type into all required fields (first name and last name)
        Thread.sleep(500);
        WebElement element = driver.findElement(By.name("firstName"));
        element.clear();
        element.sendKeys(firstName);
        element = driver.findElement(By.name("lastName"));
        element.clear();
        element.sendKeys(lastName);
        // Verify that all menu items are functional
        mainMenu.get(2).click();
        Thread.sleep(500);
        mainMenu.get(3).click();
        Thread.sleep(500);
        mainMenu.get(4).click();
        Thread.sleep(500);
        // Type into all required fields (Stop location)
        driver.findElement(By.name("locationOfStop")).clear();
        driver.findElement(By.name("locationOfStop")).sendKeys(stopLocation);
        // Create List with all roles on the road, choose role
        mainMenu.get(5).click();
        Thread.sleep(500);
        List<WebElement> roadRole = driver.findElements(By.xpath("//input[@ng-model='wizard.report.roadRole']"));
        roadRole.get(role).click();
        // Open preview
        mainMenu.get(6).click();
        Thread.sleep(500);
        mainMenu.get(7).click();

        // Verify that first name, last name and stop location same as we type in previous steps.
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='form-wizard wizard-horizontal']/div[9]/div[2]/div[1]/div[2]/div[2]/strong")).getText(), firstName+" "+lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='form-wizard wizard-horizontal']/div[9]/div[3]/div[2]/div[24]/div[2]/strong")).getText(), stopLocation);

        // Take Case ID from Preview and submit report
        String caseID = driver.findElement(By.xpath("//div[@class='form-wizard wizard-horizontal']/div[9]/div[2]/div[1]/div[1]/div[2]/strong")).getText();
        driver.findElement(By.xpath("//a[@ng-click='wizard.commitReport()']")).click();

        // Search by ID to verify that our report is created.
        driver.findElement(By.xpath("//input[@ng-model='ciSearchKey']")).sendKeys(caseID);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='media-box-body']/div[1]/div/h4")).isDisplayed());
    }

    @AfterTest
    public void breakDown()
    {
        //Sign Out and close browser
        driver.findElement(By.cssSelector(".nav.navbar-nav.navbar-right>li>a")).click();
        driver.findElement(By.cssSelector(".btn.btn-labeled.btn-primary.btn-sm")).click();
        driver.quit();
    }
}
