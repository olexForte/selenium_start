import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
//        WebDriver driver =  new EdgeDriver();

        Configuration.browser = "edge";

        Configuration.headless = false;

        Selenide.open("https://costco.com");

        assertEquals("Costco was not visited!", "Welcome to Costco Wholesale", Selenide.webdriver().object().getTitle());
        Selenide.closeWebDriver();

    }
}
