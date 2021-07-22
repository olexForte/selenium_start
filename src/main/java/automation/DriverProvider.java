package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
            default: break;
        }

        if (!browser.equals("safari")){

            String key = "webdriver.%s.driver".formatted(driverKey);

            String val = "/Users/benjaminlaird/Desktop/ForteWorkspace/selenium_start/drivers/%s".formatted(driverVal);

            System.setProperty(key, val);
        }

        ChromeOptions options = new ChromeOptions();

        if (browser.equals("chromeDecap")){
            options.addArguments("--headless");     
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1400,800");
        }

        driver = switch (browser) {
            case "chromeDecap" -> new ChromeDriver(options);
            case "firefox" -> new FirefoxDriver();
            case "safari" -> new SafariDriver();
            case "ie" -> new InternetExplorerDriver();
            default -> new ChromeDriver();
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
