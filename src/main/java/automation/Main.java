package automation;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "oleksandrdiachuk_w9V23D");
        caps.setCapability("browserstack.key", "1rzXGhpe4ZxPTdU5eSqn");

        // Set URL of the application under test
        caps.setCapability("app", "bs://98ab7ad8de1967897eef8ff4a2959063b03f07c3");

        // Specify device and os_version for testing
        caps.setCapability("device", "iPhone 11");
        caps.setCapability("os_version", "14");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "Java iOS");
        caps.setCapability("name", "first_test");


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above


        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
                new URL("http://hub-cloud.browserstack.com/wd/hub"), caps);

        driver.findElement(By.xpath("//*[@name=\"Email And Password\"]")).click();
        Thread.sleep(2000);
        System.out.println(driver.getPageSource());

        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("./target/screen.png"));
        } catch (IOException e) {
            System.out.println("Could not take screenshot");
        }

        /* Write your Custom code here */
        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();

    }
}
