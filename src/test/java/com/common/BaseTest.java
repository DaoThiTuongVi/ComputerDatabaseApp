package com.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pages.HomePage;
import com.utility.Utility;
import com.utility.webdrivers.BrowserType;
import com.utility.webdrivers.DriverFactory;
import com.utility.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashSet;


public class BaseTest {
    private DriverManager driverManager;
    protected WebDriver driver;

    public static String testCaseName;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    @BeforeSuite
    public void beforeSuite(){
        setupReport();
    }

    @BeforeClass
    public void beforeClass(){
        testCaseName = this.getClass().getSimpleName();
    }

    @BeforeMethod
    public void beforeMethod(){
        driverManager = DriverFactory.getDriverManager(BrowserType.CHROME);
        driver = driverManager.getWebDriver();
        driver.manage().window().maximize();

        extentTest = extentReports.createTest(testCaseName);
    }

    @AfterMethod
    public void afterMethod(){
        driverManager.quitWebDriver();
    }

    @AfterClass
    public void afterClass(){
        extentReports.flush();
    }

    @DataProvider
    public synchronized Object[] getDataForTest() throws IOException {
        return Utility.getData(testCaseName, GlobalVariables.DATA_FILE_PATH);
    }

    public Boolean isComputerFoundInTheList(String computerName, HomePage homePage){
        if(homePage.getHeadingText().equals(GlobalVariables.NO_COMPUTER_FOUND))
            return false;

        Boolean isFirstPage = true;
        Boolean isFound = false;

        do{
            if(!isFirstPage){
                homePage.clickNextButton();
            }

            HashSet<String> computers = homePage.getNameofAllComputersOnCurrentPage();

            if(computers.contains(computerName)){
                isFound = true;
                break;
            }

            isFirstPage = false;
        }
        while(!homePage.isNextButtonDisabled());

        return isFound;
    }

    public void setupReport(){
        // Setup reports
        htmlReporter = new ExtentHtmlReporter(GlobalVariables.OUTPUT_FOLDER_PATH + "report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle(GlobalVariables.REPORT_TITLE);
        htmlReporter.config().setReportName(GlobalVariables.REPORT_NAME);
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports.setSystemInfo("Application Name", GlobalVariables.APPLICATION_NAME);
    }

    public ExtentTest logStepInfo(String step){
        return extentTest.info(step);
    }
}
