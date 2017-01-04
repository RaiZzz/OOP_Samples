package com.crowdrise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by MadMax on 1/3/2017.
 */
public class EditProfilePage {

    WebDriver driver;
    Select select;

    public EditProfilePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement addressField()
    {
        return driver.findElement(By.xpath("//*[@id='address']"));
    }

    public WebElement cityField()
    {
        return driver.findElement(By.xpath("//*[@id='city']"));
    }

    /**
     * Only for United States.
     * @param state should contain full name of State in quotation marks
     *              Example: ("California")
     *              By default it takes "State" parameters.
     */
    public void stateUSSelect(String state)
    {
        select = new Select(driver.findElement(By.xpath("//*[@id='region']")));
        select.selectByVisibleText(state);
    }

    public void stateUSSelect()
    {
        stateUSSelect("State");
    }

    /**
     * For other countries.
     * @return Web Element "State/Province:" field.
     */
    public WebElement stateSelect()
    {
        return driver.findElement(By.xpath("//*[@id='region']"));
    }

    public WebElement zipCodeField()
    {
        return driver.findElement(By.xpath("//*[@id='zip']"));
    }

    /**
     *
     * @param country should contain full name of Country in quotation marks
     *                Example: ("United States").
     *                By default it takes "United States" parameters.
     */
    public void countrySelect(String country)
    {
        select = new Select(driver.findElement(By.xpath("//*[@id='country']")));
        select.selectByVisibleText(country);
    }

    public void countrySelect()
    {
        countrySelect("Select a Country");
    }

    public WebElement saveButton()
    {
        return driver.findElement(By.cssSelector(".button.radius.primary.small.account-button"));
    }

    public WebElement updatedSuccessfullMessage()
    {
        return driver.findElement(By.xpath("//*[@id='system_message_0']"));
    }
}
