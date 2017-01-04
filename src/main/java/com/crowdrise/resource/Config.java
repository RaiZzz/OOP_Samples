package com.crowdrise.resource;

/**
 * Created by MadMax on 1/3/2017.
 * Base variables and settings for project.
 * TODO: Modify as the non static objects.
 */
public class Config {

    private static final String baseURL = "https://www.crowdrise.com/";

    public static String getBaseURL()
    {
        return baseURL;
    }
}