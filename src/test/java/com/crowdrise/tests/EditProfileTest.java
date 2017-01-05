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
 *
 */
public class EditProfileTest extends PageObjectModelResources {

    //Create empty objects.
    WebDriver driver;
    int sleepTime;
    String country;
    String expectedResult;

    @BeforeTest
    public void setUp() throws InterruptedException
    {
        //Create new instance of WebDriver, basically open Chrome browser
        driver = new ChromeDriver();

        //Add precondition to the objects
        sleepTime = 1000;
        country = "United States";
        expectedResult = "Your account has been updated successfully";

        //Set Up test
        setUpScript(driver);

        //Add base URL into browser
        driver.get(Config.BASEURL);
        Thread.sleep(sleepTime*3);

        //Login in to CrowRise with credentials from "User" Class.
        getLogIn().loginToCrowdRise(getUser().getEmail(), getUser().getPassword());
        Thread.sleep(sleepTime*3);
    }

    /**
     * Pauses is not necessary in many places, it is created for convenient watching for
     * the test case execution.
     */
    @Test
    public void editProfileTest() throws InterruptedException
    {
        //Click on profile icon and navigate to edit profile page
        getNavBar().profileIcon().click();
        Thread.sleep(sleepTime*3);
        getNavBar().myProfileLink().click();
        Thread.sleep(sleepTime*3);
        getMyProfile().editMyProfileButton().click();
        Thread.sleep(sleepTime*3);

        //Add new random address to address field
        getEditProfile().addressField().clear();
        getEditProfile().addressField().sendKeys(getFaker().address().streetAddress());
        Thread.sleep(sleepTime*3);

        //Add new random city to city field
        getEditProfile().cityField().clear();
        getEditProfile().cityField().sendKeys(getFaker().address().city());
        Thread.sleep(sleepTime*3);

        //Add new random zip code to zip code field
        getEditProfile().zipCodeField().clear();
        getEditProfile().zipCodeField().sendKeys(getFaker().address().zipCode());
        Thread.sleep(sleepTime*3);

        //Choose new random state
        getEditProfile().stateUSSelect(getFaker().address().state());
        Thread.sleep(sleepTime*3);

        //Choose country from variable "country"
        getEditProfile().countrySelect(country);
        Thread.sleep(sleepTime*3);

        //Click save button
        getEditProfile().saveButton().click();
        Thread.sleep(sleepTime*3);

        //Verify that information saved by getting successful message same as "expectedResult".
        Assert.assertEquals(getEditProfile().updatedSuccessfulMessage().getText(), expectedResult);
    }

    @AfterTest
    public void breakDown()
    {
        //Log Out
        getNavBar().profileIcon().click();
        getNavBar().logOutLink().click();
        breakDownHelper(driver);
    }
}
