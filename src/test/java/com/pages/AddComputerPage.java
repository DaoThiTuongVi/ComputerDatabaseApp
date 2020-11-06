package com.pages;

import com.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddComputerPage extends BasePage {

    public AddComputerPage(WebDriver driver) {
        super(driver);
    }

    private By textbox_ComputerName = By.id("name");
    private By textbox_IntroducedDate = By.id("introduced");
    private By textbox_DiscontinuedDate = By.id("discontinued");
    private By dropdownlist_Company = By.id("company");
    private By button_SaveThisComputer = By.xpath("//div[contains(@class, 'actions')]/input");
    private By button_Cancel = By.xpath("//div[contains(@class, 'actions')]/a");
    private By heading_AddAComputer = By.xpath("//*[@id='main']/h1");

    public void inputComputerName(String computerName){
        if(computerName != null){
            waitForElementToBeVisible(textbox_ComputerName);
            WebElement field = driver.findElement(textbox_ComputerName);
            field.clear();
            field.sendKeys(computerName);
        }
    }

    public void inputIntroducedDate(String introducedDate){
        if(introducedDate != null){
            waitForElementToBeVisible(textbox_IntroducedDate);
            WebElement field = driver.findElement(textbox_IntroducedDate);
            field.clear();
            field.sendKeys(introducedDate);
        }
    }

    public void inputDiscontinuedDate(String discontinuedDate){
        if(discontinuedDate != null){
            waitForElementToBeVisible(textbox_DiscontinuedDate);
            WebElement field = driver.findElement(textbox_DiscontinuedDate);
            field.clear();
            field.sendKeys(discontinuedDate);
        }
    }

    public void selectCompany(String company){
        if(company != null){
            Select dropdown_Company = new Select(driver.findElement(dropdownlist_Company));

            if(company.isEmpty()){
                dropdown_Company.selectByIndex(0);
            }
            else{
                dropdown_Company.selectByVisibleText(company);
            }
        }
    }

    public void clickSaveThisComputerButton(){
        waitForElementToBeClickable(button_SaveThisComputer);
        WebElement button = driver.findElement(button_SaveThisComputer);
        button.click();
    }

    public void clickCancelButton(){
        waitForElementToBeClickable(button_Cancel);
        WebElement button = driver.findElement(button_Cancel);
        button.click();
    }

    public String getHeadingText(){
        waitForElementToBeVisible(heading_AddAComputer);
        WebElement heading = driver.findElement(heading_AddAComputer);
        return heading.getText();
    }

    public void addANewComputer(String comanyName, String introducedDate, String discontinuedDate, String company){
        inputComputerName(comanyName);
        inputIntroducedDate(introducedDate);
        inputDiscontinuedDate(discontinuedDate);
        selectCompany(company);
        clickSaveThisComputerButton();
    }
}
