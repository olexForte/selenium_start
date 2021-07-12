package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    //Declaring variables (should also be good for test cases)

    static String site = "https://www.costco.com";

    static String searchCssSelector = "search-field";

    static String searchQuery = "plastic spoon";
    
    static String searchSubmitCssSelector = "button.btn.search-ico-button";
    
    static String verifyCssSelector = "#search-results > div.product-list.grid > div:nth-child(1) > div.product-tile-set > div.thumbnail > div.caption.link-behavior > div.caption > span > a";

    //Declaring methods (good for JUnit because we can debug methods individually)

    public static void prep (){
        System.setProperty("webdriver.chrome.driver", "/Users/benjaminlaird/Desktop/ForteWorkspace/selenium_start/chromedriver");
    }

    public static void visit (WebDriver driver, String site){
        driver.get(site);
    }

    public static void typeIn (WebDriver driver, String cssSelector, String query){
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(query);
    }

    public static void clickOn (WebDriver driver, String cssSelector){
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public static void search (WebDriver driver, String searchCssSelector, String searchQuery, String searchSubmitCssSelector){
        typeIn(driver, searchCssSelector, searchQuery);
        clickOn(driver, searchSubmitCssSelector);
    }

    public static String verifyIsVisible (WebDriver driver, String verifyCssSelector){
        try {
            boolean isVisible = driver.findElement(By.cssSelector(verifyCssSelector)).isDisplayed();
            if(isVisible){
                return "The element is visible. Success!";
            } else {
                return "The element is not visible.";
            }
        } catch (Exception NoSuchElementException){
            return "We couldn't find an element matching the criteria";
        }
    }

    public static void end (WebDriver driver){
        driver.close();
        driver.quit();
    }

    //Declaring main (which combines unit methods and unit variables together)

    public static void main(String[] args) {
        
        WebDriver driver = new ChromeDriver();

        visit(driver, site);

        search(driver, searchCssSelector, searchQuery, searchSubmitCssSelector);

        System.out.println(verifyIsVisible(driver, verifyCssSelector));

        end(driver);
    }
}
