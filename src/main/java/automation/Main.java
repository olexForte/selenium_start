package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/benjaminlaird/Desktop/ForteWorkspace/selenium_start/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.costco.com"); //visits Costco website
        driver.findElement(By.id("search-field")).sendKeys("plastic spoon");
        driver.findElement(By.cssSelector("button.btn.search-ico-button")).click();
        try {
            boolean isVisible = driver.findElement(By.cssSelector("#search-results > div.product-list.grid > div:nth-child(1) > div.product-tile-set > div.thumbnail > div.caption.link-behavior > div.caption > span > a")).isDisplayed();
            if(isVisible){
                System.out.println("The element is visible. Success!");
            } else {
                System.out.println("The element is not visible.");
            }
        } catch (Exception NoSuchElementException){
            System.out.println("We couldn't find an element matching the criteria");
        }
        driver.close();
        driver.quit();
    }
}
