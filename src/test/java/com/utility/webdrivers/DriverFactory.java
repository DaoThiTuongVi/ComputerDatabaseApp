package com.utility.webdrivers;

import com.common.GlobalVariables;

public class DriverFactory {

    public static DriverManager getDriverManager(BrowserType type){
        DriverManager driverManager;

        System.setProperty("webdriver.chrome.driver", GlobalVariables.CHROME_DRIVER_PATH);
        System.setProperty("webdriver.gecko.driver", GlobalVariables.GECKO_DRIVER_PATH);

        switch (type){
            case CHROME:
                driverManager = new ChromeDriverManager();
            case FIREFOX:
                driverManager = new FirefoxDriverManager();

            default:
                driverManager = new ChromeDriverManager();

        }

        return driverManager;
    }
}
