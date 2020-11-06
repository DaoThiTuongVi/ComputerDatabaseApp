# ComputerDatabaseApp

**Automation framework summary**
- Programming language: Java
- IDE: IntelliJ
- Automation testing tool: Selenium WebDriven
- Automation framework type: data driven
- Design patterns: POM, Factory design pattern 
- Build tool: Maven
- Test framework: TestNG
- Report: ExtentReport

**Instructions for setting up and running automated tests**

1. Install Java JDK and Maven on your Windows machine
2. Install  IntelliJ and ensure TestNG plugin is installed in IntelliJ -> File -> Settings -> Plugins
3. Import the project from GIT at URL: https://github.com/DaoThiTuongVi/ComputerDatabaseApp
4. After the project is imported, the following notification pops up:
 *"Non-managed pom.xml file found <path_to_pom.xml_file>. Add as maven project or disable notification"*. Select "Add as maven project" option
5. Right click on /resources/suites/testng.xml file, select Create “<path_to_testng.xml_file>” option
6. Run/Debug Configurations dialog appears
7. Append “-Dtestng.dtd.http=true” to VM options field. This for for preventing the following exception you might get when running tests: *TestNG by default disables loading DTD from unsecured Urls. If you need to explicitly load the DTD from a http url, please do so by using the JVM argument [-Dtestng.dtd.http=true]*
8. Click OK to close the dialog
9. Run automated tests by right clicking on testng.xml file again and selecting Run “<path_to_testng.xml_file>”. 

**Note:** I’m using ChomeDriver version 86, so in order to run the tests you must user Chrome browser version 86. 
If you want to use another Chrome browser version, please 
* Download the appropriate ChromeDriver and place it in /resources/drivers folder
* Open GlobalVariables class and change CHROME_DRIVER_PATH accordingly
