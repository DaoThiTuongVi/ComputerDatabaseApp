package com.common;

public class GlobalVariables {

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String BASE_URL = "http://computer-database.herokuapp.com/computers";

    public static final int EXPLICIT_WAIT_TIMEOUT = 30;

    public static final String CHROME_DRIVER_PATH = PROJECT_PATH + "/resources/drivers/chromedriver_v86.exe";
    public static final String GECKO_DRIVER_PATH = PROJECT_PATH + "/resources/drivers/geckodriver_v0.82.0.exe";

    public static final String DATA_FILE_PATH =  PROJECT_PATH + "/resources/data/data.json";

    public static final String CHOOSE_A_COMPANY = "-- Choose a company --";
    public static final String SKIP = "SKIP";
    public static final String NO_COMPUTER_FOUND = "No computers found";
    public static final String APPLICATION_NAME =  "Computer Database";
    public static final String REPORT_TITLE = "UI Automation Report";
    public static final String REPORT_NAME = "Extent Report";

    public static final String OUTPUT_FOLDER_PATH = PROJECT_PATH + "/resources/output/";
}
