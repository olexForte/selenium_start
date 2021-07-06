package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/benjaminlaird/Desktop/ForteWorkspace/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.costco.com"); //visits Costco website
        driver.findElement(By.id("search-field")).sendKeys("rotisserie chicken");
        driver.findElement(By.cssSelector("button.btn.search-ico-button")).click();
//         try
//             {
//                 Thread.sleep(1000);
//             }
//             catch(InterruptedException ex)
//             {
//                 Thread.currentThread().interrupt();
//             }
         driver.close();
         driver.quit();
    }
}
