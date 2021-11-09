package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * A class meant to initialize a WebDriver for a given browser and use all native browser commands for it.
 */
public class DriverProvider {

    String objBrowser;

    /**
     * Instantiates a new WebDriver within a specified browser.
     * @param browser (Optional) The browser you would like to use. It supports "chromeCap" for Chrome, "chromeDecap" for headless Chrome, "firefox", "safari", and "ie". (Default) non-headless Chrome.
     */
    public DriverProvider (String browser){objBrowser = browser;}
    /**
     * Instantiates a new WebDriver within a specified browser. (Default) non-headless Chrome.
     */
    public DriverProvider (){objBrowser = "chromeCap";}

    /**
     * Instantiates a new WebDriver within a specified browser. Defaults to the value defined in {@link #objBrowser}.
     * @param browser (Optional) The browser you would like to use. It supports "chromeCap" for Chrome, "chromeDecap" for headless Chrome, "firefox", "safari", and "ie". (Default) non-headless Chrome.
     * @return A WebDriver object of the browser
     */
    public WebDriver getDriver (String browser){
        
        WebDriver driver;

        //Setting default browser settings.
        String driverKey = "chrome";

        String driverVal = "chromedriver";

        //answer found here: https://artoftesting.com/launching-browsers-in-selenium
        switch (browser){
            case "firefox": {
                //using geckodriver
                driverKey = "gecko";
                driverVal = "geckodriver";
                break;
            }
            case "ie": {
                driverKey = "ie";
                //using IEDriveServer
                driverVal = "IEDriverServer";
                break;
            }
            case "edge" : driver = new EdgeDriver(); break;
            default: break;
        }

        if (!browser.equals("safari")){

            String key = String.format("webdriver.%s.driver", driverKey);

            String val = String.format("/Users/benjaminlaird/Desktop/ForteWorkspace/selenium_start/drivers/%s", driverVal);

            System.setProperty(key, val);
        }

        ChromeOptions options = new ChromeOptions();

        if (browser.equals("chromeDecap")){
            options.addArguments("--headless");     
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1400,800");
        }

        switch (browser) {
            case "chromeDecap" :
                driver = new ChromeDriver(options); break;
            case "firefox" : driver = new FirefoxDriver(); break;
            case "safari" : driver = new SafariDriver(); break;
            case "ie" : driver = new InternetExplorerDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            default : driver = new ChromeDriver();
        };

        return driver;
    }
    /**
     * Defaults to the {@link #objBrowser} value defined when the class was instantiated.
     * @return The WebDriver of the {@link #objBrowser}.
     */
    public WebDriver getDriver (){
        return getDriver(objBrowser);
    }
}
