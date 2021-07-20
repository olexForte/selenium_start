package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A class that represents a webpage with actions, like typing into fields, clicking on items, closing tabs or quitting the browser.
 */
public class Page {

    String objSite;

    WebDriver driver;

    /**
     * The initializer for the Page class.
     * @param site The site you would like to open.
     * @param browser (Optional) The browser you would like to use. (Default) The default defined in the {@link DriverProvider} class.
     */
    public Page (String site, String browser){
        driver = new DriverProvider(browser).getDriver();
        objSite = site;
        visit();
    }
    /**
     * The initializer for the Page class. Defaults to the default defined in the {@link DriverProvider} class.
     * @param site The site you would like to open.
     */
    public Page (String site){
        driver = new DriverProvider().getDriver();
        objSite = site;
        visit();
    }

    /**
     * Visit a website.
     * @param site (Optional) The site you would like to visit. (Default) The site defined in the {@link #objSite} variable.
     */
    public void visit (String site){
        driver.get(site);
    }
    /**
     * Visit a website. (Default) The site defined in the {@link #objSite} variable.
     */
    public void visit (){
        driver.get(objSite);
    }

    /**
     * Type in a string to a field specified by a CSS selector.
     * @param cssSelector The CSS selector targeting the field you would like to type to.
     * @param query The string you would like to type.
     */
    public void typeIn (String cssSelector, String query){
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(query);
    }

    /**
     * Click on an element.
     * @param cssSelector The CSS selector targeting the button or other object you would like to click on.
     */
    public void clickOn (String cssSelector){
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    /**
     * Hold operation for a certain amount of time.
     * @param milliseconds The amount of time to hold on, in milliseconds.
     * @see #Thread.sleep(long)
     */
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

    /**
     * Verify if an element is visible.
     * @param verifyCssSelector The CSS selector targeting the element you would like to test.
     * @return A string describing whether the search was successful and if the element is visible.
     */
    public String verifyIsVisible (String verifyCssSelector){
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

    /**
     * Close the tab.
     */
    public void closeTab (){
        driver.close();
    }

    /**
     * Quit the browser.
     */
    public void stop (){
        driver.quit();
    }
}