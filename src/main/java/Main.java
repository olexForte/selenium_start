import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/benjaminlaird/Desktop/ForteWorkspace/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.walmart.com");
        driver.findElement(By.cssSelector("#global-search-dropdown-toggle")).click();
        driver.findElement(By.cssSelector("button[data-catid='3920']")).click();
        driver.findElement(By.cssSelector("#global-search-input")).sendKeys("Hobbit");
        driver.findElement(By.cssSelector("#global-search-submit")).click();
        /*
         try
             {
                 Thread.sleep(1000);
             }
             catch(InterruptedException ex)
             {
                 Thread.currentThread().interrupt();
             }
         driver.close();
         driver.quit();
        */
    }
}
