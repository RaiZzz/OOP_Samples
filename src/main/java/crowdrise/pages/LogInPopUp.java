package crowdrise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by MadMax on 1/3/2017.
 */
public class LogInPopUp {

    WebDriver driver;

    public LogInPopUp(WebDriver driver)
    {
        this.driver = driver;
    }


    public WebElement emailAddressField()
    {
        return driver.findElement(By.xpath("//*[@id='login_email']"));
    }

    public WebElement passwordField()
    {
        return driver.findElement(By.xpath("//*[@id='login_password']"));
    }

    public WebElement logInButton()
    {
        return driver.findElement(By.xpath("//*[@id='popup_login_submit']"));
    }

    public void loginToCrowdRise(String email, String password) throws InterruptedException
    {
        NavigateBar navigateBar = new NavigateBar(driver);
        navigateBar.logInLink().click();
        Thread.sleep(3000);
        emailAddressField().clear();
        emailAddressField().sendKeys(email);
        Thread.sleep(3000);
        passwordField().clear();
        passwordField().sendKeys(password);
        Thread.sleep(3000);
        logInButton().click();
    }
}
