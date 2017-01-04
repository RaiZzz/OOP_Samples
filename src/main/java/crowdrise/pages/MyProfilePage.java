package crowdrise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by MadMax on 1/3/2017.
 */
public class MyProfilePage {

    WebDriver driver;

    public MyProfilePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement editMyProfileButton()
    {
        return driver.findElement(By.cssSelector(".button.lightestGreyGrad.buttonProfile"));
    }


}
