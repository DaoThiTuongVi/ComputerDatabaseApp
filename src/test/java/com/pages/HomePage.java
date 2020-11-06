package com.pages;

import com.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By button_AddANewComputer = By.id("add");
    private By textbox_Search = By.id("searchbox");
    private By button_FilterByName = By.id("searchsubmit");
    private By button_Previous = By.xpath("//div[@id='pagination']//li[contains(@class,'prev')]/a");
    private By button_Next = By.xpath("//div[@id='pagination']//li[contains(@class,'next')]/a");
    private By li_tag_Next = By.xpath("//div[@id='pagination']//li[contains(@class,'next')]");
    private By heading_CertainNumberOfComputersFound = By.xpath("//*[@id='main']/h1");
    private By linksOfComputersOnCurrentPage = By.xpath("//table[contains(@class,'computers')]/tbody/tr/td[1]/a");
    private By alert_Message = By.xpath("//div[contains(@class, 'alert-message')]");

    private String dynamic_Cell_ComputerName = "//table[contains(@class,'computers')]/tbody/tr/td[1]/a[text()='<computer_name>']";
    private String dynamic_Cell_Introduced = "//table[contains(@class,'computers')]/tbody/tr/td[1]/a[text()='<computer_name>']/..//following-sibling::td[1]/em | //table[contains(@class,'computers')]/tbody/tr/td[1]/a[text()='<computer_name>']/..//following-sibling::td[1]";
    private String dynamic_Cell_Discontinued = "//table[contains(@class,'computers')]/tbody/tr/td[1]/a[text()='<computer_name>']/..//following-sibling::td[2]/em | //table[contains(@class,'computers')]/tbody/tr/td[1]/a[text()='<computer_name>']/..//following-sibling::td[2]";
    private String dynamic_Cell_Company = "//table[contains(@class,'computers')]/tbody/tr/td[1]/a[text()='<computer_name>']/..//following-sibling::td[3]/em | //table[contains(@class,'computers')]/tbody/tr/td[1]/a[text()='<computer_name>']/..//following-sibling::td[3]";

    public void inputKeywordToSearchBox(String keyword){
        waitForElementToBeVisible(textbox_Search);
        WebElement field = driver.findElement(textbox_Search);
        field.clear();
        field.sendKeys(keyword);
    }

    public void clickFilterByNameButton(){
        waitForElementToBeClickable(button_FilterByName);
        WebElement button = driver.findElement(button_FilterByName);
        button.click();
    }

    public void clickANewComputerButton(){
        waitForElementToBeClickable(button_AddANewComputer);
        WebElement button = driver.findElement(button_AddANewComputer);
        button.click();
    }

    public void clickPreviousButton(){
        waitForElementToBeClickable(button_Previous);
        WebElement button = driver.findElement(button_Previous);
        button.click();
    }

    public void clickNextButton(){
        waitForElementToBeClickable(button_Next);
        WebElement button = driver.findElement(button_Next);
        button.click();
    }

    public String getHeadingText(){
        waitForElementToBeVisible(heading_CertainNumberOfComputersFound);
        WebElement heading = driver.findElement(heading_CertainNumberOfComputersFound);
        return heading.getText();
    }

    public HashSet<String> getNameofAllComputersOnCurrentPage(){
        waitForAllElementsToBeVisible(linksOfComputersOnCurrentPage);
        List<WebElement> list = driver.findElements(linksOfComputersOnCurrentPage);
        HashSet<String> computers = new HashSet<>();
        for(WebElement element: list){
            computers.add(element.getText());
        }
        return computers;
    }

    public void clickLinkOf(String computerName){
        By locator = By.xpath(dynamic_Cell_ComputerName.replace("<computer_name>", computerName));
        waitForElementToBeClickable(locator);
        WebElement link = driver.findElement(locator);
        link.click();
    }

    public String getIntroducedDateOf(String computerName){
        By locator = By.xpath(dynamic_Cell_Introduced.replace("<computer_name>", computerName));
        waitForElementToBeVisible(locator);
        WebElement cell = driver.findElement(locator);
        return (cell.getText().equals("-") ? "" : cell.getText());
    }

    public String getDiscontinuedDateOf(String computerName){
        By locator = By.xpath(dynamic_Cell_Discontinued.replace("<computer_name>", computerName));
        waitForElementToBeVisible(locator);
        WebElement cell = driver.findElement(locator);
        return (cell.getText().equals("-") ? "" : cell.getText());
    }

    public String getCompanyOf(String computerName){
        By locator = By.xpath(dynamic_Cell_Company.replace("<computer_name>", computerName));
        waitForElementToBeVisible(locator);
        WebElement cell = driver.findElement(locator);
        return (cell.getText().equals("-") ? "" : cell.getText());
    }

    public String getAlertMessage(){
        waitForElementToBeVisible(alert_Message);
        WebElement alertMessage = driver.findElement(alert_Message);
        return alertMessage.getText();
    }

    public Boolean isNextButtonDisabled(){
        waitForElementToBeVisible(li_tag_Next);
        WebElement element = driver.findElement(li_tag_Next);
        return element.getAttribute("class").contains("disabled");
    }

    public void searchByName(String computerName){
        inputKeywordToSearchBox(computerName);
        clickFilterByNameButton();
    }
}
