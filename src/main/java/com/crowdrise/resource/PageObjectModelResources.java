package com.crowdrise.resource;

import com.crowdrise.pages.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by MadMax on 1/3/2017.
 */
public class PageObjectModelResources {

    private MainPage main;
    private NavigateBar navBar;

    private LogInPopUp logIn;

    private MyProfilePage myProfile;
    private EditProfilePage editProfile;

    private User user;
    private Faker faker;

    public void setUpScript(WebDriver driver)
    {
        //implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        main = new MainPage();
        navBar = new NavigateBar(driver);

        logIn = new LogInPopUp(driver);

        myProfile = new MyProfilePage(driver);
        editProfile = new EditProfilePage(driver);

        user = new User();
        faker = new Faker(new Locale("en-US"));
    }

    protected MainPage getMain() {
        return main;
    }

    protected NavigateBar getNavBar(){
        return navBar;
    }

    protected LogInPopUp getLogIn(){
        return logIn;
    }

    protected MyProfilePage getMyProfile(){
        return myProfile;
    }

    protected EditProfilePage getEditProfile(){
        return editProfile;
    }

    protected User getUser(){
        return user;
    }

    protected Faker getFaker(){
        return faker;
    }
    protected void breakDownHelper(WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
