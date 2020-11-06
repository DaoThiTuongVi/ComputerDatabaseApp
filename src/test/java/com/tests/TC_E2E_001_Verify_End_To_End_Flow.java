package com.tests;

import com.common.BaseTest;
import com.common.GlobalVariables;
import com.pages.AddComputerPage;
import com.pages.EditComputerPage;
import com.pages.HomePage;
import com.utility.Utility;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TC_E2E_001_Verify_End_To_End_Flow extends BaseTest {
    private HomePage homePage;
    private AddComputerPage addComputerPage;
    private EditComputerPage editComputerPage;

    private String initialComputerName, intialIntroducedDate, initialDiscontinuedDate, initialCompany;
    private String newComputerName, newIntroducedDate, newDiscontinuedDate, newCompany;
    private String currentComputerName,currentIntroducedDate, currentDiscontinuedDate, currentCompany;
    private String deletingSuccessfulMsg, addingSuccessfulMsg, editingSuccessfulMsg;


    @Test (dataProvider = "getDataForTest")
    public void TC01(HashMap<String, String> data) {

        initialComputerName = data.get("InitialComputerName");
        intialIntroducedDate = data.get("InitialIntroducedDate");
        initialDiscontinuedDate = data.get("InitialDiscontinuedDate");
        initialCompany = data.get("InitialCompany");

        newComputerName = data.get("NewComputerName");
        newIntroducedDate = data.get("NewIntroducedDate");
        newDiscontinuedDate = data.get("NewDiscontinuedDate");
        newCompany = data.get("NewCompany");

        deletingSuccessfulMsg =  data.get("DeletingSuccessfulMsg");
        addingSuccessfulMsg =  data.get("AddingSuccessfulMsg");
        editingSuccessfulMsg = data.get("EditingSuccessfulMsg");

        logStepInfo("Step 1. Navigate to Home page");
        driver.get(GlobalVariables.BASE_URL);
        homePage = new HomePage(driver);

        logStepInfo("Step 2. Click Add a new computer button");
        homePage.clickANewComputerButton();
        addComputerPage = new AddComputerPage(driver);

        logStepInfo("Step 3. Fill in the form and click Save this computer button");
        addComputerPage.addANewComputer(initialComputerName, intialIntroducedDate, initialDiscontinuedDate, initialCompany);
        homePage = new HomePage(driver);

        logStepInfo("Step 4. Verify alert message after adding the computer on Home page");
        Assert.assertEquals(addingSuccessfulMsg.replace("<computer_name>", initialComputerName), homePage.getAlertMessage());

        logStepInfo("Step 5.  Search the newly added computer");
        homePage.searchByName(initialComputerName);

        logStepInfo("Step 6. Verify if it's found");
        Assert.assertTrue(isComputerFoundInTheList(initialComputerName, homePage));

        logStepInfo("Step 7. Verify info of newly added computer on Home page");
        Assert.assertEquals(Utility.convertStringToDate(intialIntroducedDate, "yyyy-MM-dd"),
                Utility.convertStringToDate(homePage.getIntroducedDateOf(initialComputerName), "dd MMM yyyy"));
        Assert.assertEquals(Utility.convertStringToDate(initialDiscontinuedDate, "yyyy-MM-dd"),
                Utility.convertStringToDate(homePage.getDiscontinuedDateOf(initialComputerName), "dd MMM yyyy"));
        Assert.assertEquals(initialCompany, homePage.getCompanyOf(initialComputerName));

        logStepInfo("Step 8. Click on the computer's name to open Edit computer page");
        homePage.clickLinkOf(initialComputerName);
        editComputerPage = new EditComputerPage(driver);

        logStepInfo("Step 9. Verify info of newly added computer on Edit computer page");
        Assert.assertEquals(initialComputerName, editComputerPage.getComputerName());
        Assert.assertEquals(intialIntroducedDate, editComputerPage.getIntroducedDate());
        Assert.assertEquals(initialDiscontinuedDate, editComputerPage.getDiscontinuedDate());
        Assert.assertEquals(initialCompany, editComputerPage.getCompany());

        logStepInfo("Step 10. Edit the computer's info");
        editComputerPage.editComputer(newComputerName, newIntroducedDate, newDiscontinuedDate, newCompany);
        homePage = new HomePage(driver);

        currentComputerName = (newComputerName.equals(GlobalVariables.SKIP) ? initialComputerName : newComputerName);
        currentIntroducedDate = (newIntroducedDate.equals(GlobalVariables.SKIP) ? intialIntroducedDate : newIntroducedDate);
        currentDiscontinuedDate = (newDiscontinuedDate.equals(GlobalVariables.SKIP) ? initialDiscontinuedDate : newDiscontinuedDate);
        currentCompany = (newCompany.equals(GlobalVariables.SKIP) ? initialCompany:  newCompany);

        logStepInfo("Step 11. Verify the alert message after editing the computer on Home page");
        Assert.assertEquals(editingSuccessfulMsg.replace("<computer_name>", currentComputerName), homePage.getAlertMessage());

        logStepInfo("Step 12. Search the name edited computer");
        homePage.searchByName(currentComputerName);

        logStepInfo("Step 13. Verify if it's found");
        Assert.assertTrue(isComputerFoundInTheList(currentComputerName, homePage));

        logStepInfo("Step 14. Verify info of edited computer on Home page");
        Assert.assertEquals(Utility.convertStringToDate(currentIntroducedDate, "yyyy-MM-dd"),
                Utility.convertStringToDate(homePage.getIntroducedDateOf(currentComputerName), "dd MMM yyyy"));
        Assert.assertEquals(Utility.convertStringToDate(currentDiscontinuedDate, "yyyy-MM-dd"),
                Utility.convertStringToDate(homePage.getDiscontinuedDateOf(currentComputerName), "dd MMM yyyy"));
        Assert.assertEquals(currentCompany, homePage.getCompanyOf(currentComputerName));

        logStepInfo("Step 15. Click on the computer's link to go to Edit computer page");
        homePage.clickLinkOf(currentComputerName);
        editComputerPage = new EditComputerPage(driver);

        logStepInfo("Step 16. Verify info of edited computer on Edit computer page");
        Assert.assertEquals(currentComputerName, editComputerPage.getComputerName());
        Assert.assertEquals(currentIntroducedDate, editComputerPage.getIntroducedDate());
        Assert.assertEquals(currentDiscontinuedDate, editComputerPage.getDiscontinuedDate());
        Assert.assertEquals(currentCompany, editComputerPage.getCompany());

        logStepInfo("Step 17. Click Delete this computer button");
        editComputerPage.clickDeleteButton();
        homePage = new HomePage(driver);

        logStepInfo("Step 18. Verify alert message after deleting the computer");
        Assert.assertEquals(deletingSuccessfulMsg.replace("<computer_name>", currentComputerName), homePage.getAlertMessage());

        logStepInfo("Step 19. Search for deleted computer");
        homePage.searchByName(currentComputerName);

        logStepInfo("Step 20. Verify deleted computer is no longer found");
        Assert.assertFalse(isComputerFoundInTheList(currentComputerName, homePage));
    }
}
