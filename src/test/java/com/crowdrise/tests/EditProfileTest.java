package com.crowdrise.tests;

import com.crowdrise.resource.Config;
import com.crowdrise.resource.PageObjectModelResources;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by MadMax on 1/3/2017.
 */
public class EditProfileTest extends PageObjectModelResources {

    WebDriver driver;
    int sleepTime;
    String country = "United States";
    String expectedResult = "Your account has been updated successfully";

    @BeforeTest
    public void setUp() throws InterruptedException {

        driver = new ChromeDriver();
        sleepTime = 1000;

        setUpScript(driver);

        driver.get(Config.getBaseURL());
        Thread.sleep(sleepTime*5);

        getLogIn().loginToCrowdRise(getUser().getEmail(), getUser().getPassword());
        Thread.sleep(sleepTime*5);
    }

    @Test
    public void editProfileTest() throws InterruptedException
    {

        getNavBar().profileIcon().click();
        Thread.sleep(sleepTime*3);

        getNavBar().myProfileLink().click();
        Thread.sleep(sleepTime*3);

        getMyProfile().editMyProfileButton().click();
        Thread.sleep(sleepTime*3);

        getEditProfile().addressField().clear();
        getEditProfile().addressField().sendKeys(getFaker().address().streetAddress());
        Thread.sleep(sleepTime*3);

        getEditProfile().cityField().clear();
        getEditProfile().cityField().sendKeys(getFaker().address().city());
        Thread.sleep(sleepTime*3);

        getEditProfile().zipCodeField().clear();
        getEditProfile().zipCodeField().sendKeys(getFaker().address().zipCode());
        Thread.sleep(sleepTime*3);

        getEditProfile().stateUSSelect(getFaker().address().state());
        Thread.sleep(sleepTime*3);

        getEditProfile().countrySelect(country);
        Thread.sleep(sleepTime*3);

        getEditProfile().saveButton().click();
        Thread.sleep(sleepTime*3);

        Assert.assertEquals(getEditProfile().updatedSuccessfullMessage().getText(), expectedResult);
    }

    @AfterTest
    public void breakDown()
    {
        breakDownHelper(driver);
    }
}
