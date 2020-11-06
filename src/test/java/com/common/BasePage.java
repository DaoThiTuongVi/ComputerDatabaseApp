package com.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, GlobalVariables.EXPLICIT_WAIT_TIMEOUT);
    }


    public void waitForElementToBeClickable(By locator){
        new WebDriverWait(driver, GlobalVariables.EXPLICIT_WAIT_TIMEOUT).
                until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeVisible(By locator){
        new WebDriverWait(driver, GlobalVariables.EXPLICIT_WAIT_TIMEOUT).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForAllElementsToBeVisible(By locator){
        new WebDriverWait(driver, GlobalVariables.EXPLICIT_WAIT_TIMEOUT).
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
