package com.pages;

import com.common.BasePage;
import com.common.GlobalVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EditComputerPage extends BasePage {

    public EditComputerPage(WebDriver driver) {
        super(driver);
    }

    private By textbox_ComputerName = By.id("name");
    private By textbox_IntroducedDate = By.id("introduced");
    private By textbox_DiscontinuedDate = By.id("discontinued");
    private By dropdownlist_Company = By.id("company");
    private By button_SaveThisComputer = By.xpath("//div[contains(@class, 'actions')]/input");
    private By button_Cancel = By.xpath("//div[contains(@class, 'actions')]/a");
    private By button_DeleteThisComputer = By.xpath("//form[contains(@action, 'delete')]/input");

    public void inputComputerName(String computerName){
        if(!(computerName == null | computerName.equals(GlobalVariables.SKIP))){
            waitForElementToBeVisible(textbox_ComputerName);
            WebElement field = driver.findElement(textbox_ComputerName);
            field.clear();
            field.sendKeys(computerName);
        }
    }

    public void inputIntroducedDate(String introducedDate){
        if(!(introducedDate == null & introducedDate.equals(GlobalVariables.SKIP))){
            waitForElementToBeVisible(textbox_IntroducedDate);
            WebElement field = driver.findElement(textbox_IntroducedDate);
            field.clear();
            field.sendKeys(introducedDate);
        }
    }

    public void inputDiscontinuedDate(String discontinuedDate){
        if(!(discontinuedDate == null | discontinuedDate.equals(GlobalVariables.SKIP))){
            waitForElementToBeVisible(textbox_DiscontinuedDate);
            WebElement field = driver.findElement(textbox_DiscontinuedDate);
            field.clear();
            field.sendKeys(discontinuedDate);
        }
    }

    public void selectCompany(String company){
        if(!(company == null | company.equals(GlobalVariables.SKIP))){
            Select dropdown_Company = new Select(driver.findElement(dropdownlist_Company));

            if(company.isEmpty()){
                dropdown_Company.selectByIndex(0);
            }
            else{
                dropdown_Company.selectByVisibleText(company);
            }
        }
    }

    public String getComputerName(){
        waitForElementToBeVisible(textbox_ComputerName);
        return driver.findElement(textbox_ComputerName).getAttribute("value");
    }

    public String getIntroducedDate(){
        waitForElementToBeVisible(textbox_IntroducedDate);
        return driver.findElement(textbox_IntroducedDate).getAttribute("value");
    }

    public String getDiscontinuedDate(){
        waitForElementToBeVisible(textbox_DiscontinuedDate);
        return driver.findElement(textbox_DiscontinuedDate).getAttribute("value");
    }

    public String getCompany(){
        waitForElementToBeVisible(dropdownlist_Company);
        Select select = new Select(driver.findElement(dropdownlist_Company));
        String company = select.getFirstSelectedOption().getText();
        return (company.equals(GlobalVariables.CHOOSE_A_COMPANY) ? "" : company);
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

    public void clickDeleteButton(){
        waitForElementToBeClickable(button_DeleteThisComputer);
        WebElement button = driver.findElement(button_DeleteThisComputer);
        button.click();
    }

    public void editComputer(String newCompanyName, String newIntroducedDate, String newDiscontinuedDate, String newCompany){
        inputComputerName(newCompanyName);
        inputIntroducedDate(newIntroducedDate);
        inputDiscontinuedDate(newDiscontinuedDate);
        selectCompany(newCompany);
        clickSaveThisComputerButton();
    }
}
