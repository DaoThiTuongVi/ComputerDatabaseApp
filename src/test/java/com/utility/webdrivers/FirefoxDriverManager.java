package com.utility.webdrivers;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager{

    @Override
    protected void createWebDriver() {
        driver = new FirefoxDriver();
    }
}
