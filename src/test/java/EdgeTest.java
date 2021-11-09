import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.codeborne.selenide.Configuration;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EdgeTest {

    @Test
    public void getDriver(){
//        DesiredCapabilities caps = new DesiredCapabilities();//DesiredCapabilities.firefox();
//        //caps.setBrowserName("ie");
//        caps.setBrowserName("edge");
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, Level.ALL);
//        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
////        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);
//
//                WebDriverManager.edgedriver().setup();
////                WebDriverManager.iedriver().version().setup();
//
        EdgeOptions opt = new EdgeOptions();
        opt.setCapability("headless", true);
       // opt.setCapability("remote-debugging-port", 185);
        opt.setCapability("disable-dev-shm-usage" , true);
        opt.setCapability("verbose" , true);
                System.setProperty("webdriver.edge.driver", "./msedgedriver");
        WebDriver driver =  new EdgeDriver(opt);


        driver.get("https://costco.com");
        assertEquals("Costco was not visited!", "Welcome to Costco Wholesale", driver.getTitle());
        driver.close();
        driver.quit();

//        Configuration.browser = "edge";
//
//        Configuration.headless = true;
//
//        EdgeOptions opt = new EdgeOptions();
//
//
//        DesiredCapabilities caps = new DesiredCapabilities();//DesiredCapabilities.firefox();
//        caps.setCapability();
//
//        Configuration.browserCapabilities = new DesiredCapabilities();
//
//        "--remote-debugging-port=<port>";
//        "--headless";
//        System.setProperty("webdriver.edge.driver", "./msedgedriver");
//
//        Configuration.reportsFolder = ".";
//        Configuration.screenshots = true;
//
//        Selenide.open("https://costco.com");
//
//        assertEquals("Welcome to Costco Wholesale", Selenide.title(), "Costco was not visited!");
//        Selenide.closeWebDriver();

    }
}
