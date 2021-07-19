package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * A class meant to initialize a WebDriver for a given browser and use all the native browser commands for it.
 */
public class DriverProvider {

    String objBrowser;

    /**
     * Instantiates a new WebDriver within a specified browser. When blank, it defaults to the value defined in {@link #objBrowser}
     * @param browser The browser you would like to use. It supports "chrome", "firefox", "safari", and "ie". It defaults to Chrome.
     */
    public DriverProvider (String browser){
        objBrowser = browser;
    }
    /**
     * Defaults to "chrome".
     */
    public DriverProvider (){
        objBrowser = "chrome";
    }

    /**
     * Instantiates a new WebDriver within a specified browser. When blank, it defaults to the value defined in {@link #objBrowser}
     * @param browser The browser you would like to use. It supports "chrome", "firefox", "safari", and "ie". It defaults to Chrome.
     * @return A WebDriver object of the browser
     */
    public WebDriver getDriver (String browser){
        
        WebDriver driver;

        String driverKey = "chrome";

        String driverVal = "chromedriver";

        //answer found here: https://artoftesting.com/launching-browsers-in-selenium

        switch (browser){
            case "chrome": {break;}
            case "firefox": {
                //using geckodriver
                driverKey = "gecko";
                driverVal = "geckodriver";
                break;
            }
            case "safari": {
                driverKey = "safari";
                break;
            }
            case "ie": {
                driverKey = "ie";
                //using IEDriveServer
                driverVal = "IEDriverServer";
                break;
            }
            default: break;
        }

        if (!driverKey.equals("safari")){

            String key = "webdriver.%s.driver".formatted(driverKey);

            String val = "/Users/benjaminlaird/Desktop/ForteWorkspace/selenium_start/drivers/%s".formatted(driverVal);

            System.setProperty(key, val);
        }

        switch (browser){
            case "chrome": driver = new ChromeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            case "safari": driver = new SafariDriver(); break;
            case "ie": driver = new InternetExplorerDriver(); break;
            default: driver = new ChromeDriver(); break;
        }

        return driver;
    }
    /**
     * Defaults to the {@link #objBrowser} value defined when the class was instantiated
     * @return The WebDriver of the {@link #objBrowser}
     */
    public WebDriver getDriver (){
        return getDriver(objBrowser);
    }
}
