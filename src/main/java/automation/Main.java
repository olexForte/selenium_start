package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

class TestDriver extends ChromeDriver {

    //Declaring variables (should also be good for test cases)

    String targetSite = "https://www.costco.com";

    String searchCssSelector = "#search-field";

    String searchQuery = "plastic spoon";
    
    String searchSubmitCssSelector = "button.btn.search-ico-button";
    
    String verifyCssSelector = "#search-results > div.product-list.grid > div:nth-child(1) > div.product-tile-set > div.thumbnail > div.caption.link-behavior > div.caption > span > a";    

    //Declaring methods (good for JUnit because we can debug methods individually)

    public void prep (){
        System.setProperty("webdriver.chrome.driver", "/Users/benjaminlaird/Desktop/ForteWorkspace/selenium_start/chromedriver");
    }

    public void visit (String site){
        get(site);
    }

    public void typeIn (String cssSelector, String query){
        findElement(By.cssSelector(cssSelector)).sendKeys(query);
    }

    public void clickOn (String cssSelector){
        findElement(By.cssSelector(cssSelector)).click();
    }

    public void search (String searchCssSelector, String searchQuery, String searchSubmitCssSelector){
        typeIn(searchCssSelector, searchQuery);
        clickOn(searchSubmitCssSelector);
    }

    public void holdOn (long milliseconds){
        System.out.println("Waiting...");
        try
        {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Running...");
    }

    public String verifyIsVisible (String verifyCssSelector){
        try {
            boolean isVisible = findElement(By.cssSelector(verifyCssSelector)).isDisplayed();
            if(isVisible){
                return "The element is visible. Success!";
            } else {
                return "The element is not visible.";
            }
        } catch (Exception NoSuchElementException){
            return "We couldn't find an element matching the criteria";
        }
    }

    public void end (){
        close();
        quit();
    }

}

public class Main {

    public static void main(String[] args) {
        
        TestDriver driver = new TestDriver();

        driver.prep();

        driver.visit(driver.targetSite);

        driver.holdOn(1000);

        driver.search(driver.searchCssSelector, driver.searchQuery, driver.searchSubmitCssSelector);

        driver.holdOn(5000);

        System.out.println(driver.verifyIsVisible(driver.verifyCssSelector));

        driver.end();
    }
}
