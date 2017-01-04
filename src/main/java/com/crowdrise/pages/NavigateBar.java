package com.crowdrise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by MadMax on 1/3/2017.
 */
public class NavigateBar {

    WebDriver driver;

    public NavigateBar(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement logInLink()
    {
        return driver.findElement(By.linkText("Log In"));
    }

    public WebElement profileIcon()
    {
        return driver.findElement(By.cssSelector(".user-photo.has-dropdown>a"));
    }

    public WebElement myProfileLink()
    {
        return driver.findElement(By.xpath("//*[@id='user-menu-dropdown']/ul/li[2]/label/a"));
    }

    public WebElement logOutLink()
    {
        return driver.findElement(By.xpath("//*[@id='logout_menu_button']"));
    }
}
