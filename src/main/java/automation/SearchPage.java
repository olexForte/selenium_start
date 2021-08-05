package automation;

import org.openqa.selenium.By;

public class SearchPage extends Page {
    
    public String objSearchCssSelector;

    public String objQuery;

    public String objSubmitCssSelector;

    /**
     * Initialize a Page equipped with search features. (Default) The default defined in the {@link DriverProvider} class.
     * @param site The site you would like to open.
     */
    public SearchPage (String site){
        super(site);

        objSearchCssSelector = "Placeholder";
        objQuery = "Placeholder";
        objSubmitCssSelector = "Placeholder";
    }
    /**
     * Initialize a Page equipped with search features. (Default) The default defined in the {@link DriverProvider} class.
     * @param site The site you would like to open.
     * @param browserMode (Optional) The browser you would like to use. (Default) The default defined in the {@link DriverProvider} class.
     */
    public SearchPage (String site, String browserMode){
        super(site, browserMode);

        objSearchCssSelector = "Placeholder";
        objQuery = "Placeholder";
        objSubmitCssSelector = "Placeholder";
    }
    /**
     * Initializes a Page equipped with search features.
     * @param site The site you would like to open.
     * @param browserMode (Optional) The browser you would like to use. (Default) The default defined in the {@link DriverProvider} class.
     * @param searchCssSelector (Optional) The CSS selector targeting the search field you would like to type to. (Default) The "Placeholder" string.
     * @param query (Optional) The string you would like to type. (Default) The "Placeholder" string.
     * @param submitCssSelector (Optional) The CSS selector targeting the submit button. (Default) The "Placeholder" string.
     */
    public SearchPage (String site, String browserMode, String searchCssSelector, String query, String submitCssSelector){
        super(site, browserMode);

        objSearchCssSelector = searchCssSelector;
        objQuery = query;
        objSubmitCssSelector = submitCssSelector;
    }
    
    /**
     * Initialize a Page equipped with search features. (Default) The default defined in the {@link DriverProvider} class.
     * @param site The site you would like to open.
     * @param searchCssSelector (Optional) The CSS selector targeting the search field you would like to type to. (Default) The "Placeholder" string.
     * @param submitCssSelector (Optional) The CSS selector targeting the submit button. (Default) The "Placeholder" string.
     */
    public SearchPage (String site, String searchCssSelector, String submitCssSelector){
        super(site);

        objSearchCssSelector = searchCssSelector;
        objQuery = "Placeholder";
        objSubmitCssSelector = submitCssSelector;
    }
    /**
     * Initialize a Page equipped with search features. (Default) The default defined in the {@link DriverProvider} class.
     * @param site The site you would like to open.
     * @param searchCssSelector (Optional) The CSS selector targeting the search field you would like to type to. (Default) The "Placeholder" string.
     * @param submitCssSelector (Optional) The CSS selector targeting the submit button. (Default) The "Placeholder" string.
     */
    public SearchPage (String site, String browserMode, String searchCssSelector, String submitCssSelector){
        super(site,browserMode);

        objSearchCssSelector = searchCssSelector;
        objQuery = "Placeholder";
        objSubmitCssSelector = submitCssSelector;
    }
    
    /**
     * Search a Page.
     * @param searchCssSelector (Optional) The CSS selector targeting the search field you would like to type to. (Default) The default defined in the {@link #objSearchCssSelector} variable.
     * @param query The string you would like to type.
     * @param submitCssSelector (Optional) The CSS selector targeting the submit button. (Default) The default defined in the {@link #objSubmitCssSelector} variable.
     */
    public void search (String searchCssSelector, String query, String submitCssSelector){
        holdOn(5000);
        typeIn(searchCssSelector, query);
        clickOn(submitCssSelector);
    }
    /**
     * Search a Page. (Defaults) The defaults defined in the {@link #objSearchCssSelector} and {@link #objSubmitCssSelector} variables.
     * @param query The string you would like to type.
     */
    public void search (String query){
        holdOn(5000);
        typeIn(objSearchCssSelector, query);
        clickOn(objSubmitCssSelector);
    }

    /**
     * Verify that a particular element on a page is displayed to the browser.
     * @param verifyCssSelector The CSS selector targeting the element you would like to verify.
     * @return An exit code from 0 to 2. 0 means the element is displayed. 1 means the element is not displayed. 2 means the element could not be found.
     */
    public int verify (String verifyCssSelector){
        
        int exitCode;

        try {
            boolean isVisible = driver.findElement(By.cssSelector(verifyCssSelector)).isDisplayed();
            
            if (isVisible){
                exitCode = 0;
            } else {
                exitCode = 1;
            }
        } catch (Exception noSuchElemeException) {
            exitCode = 2;
        }

        return exitCode;
    }
}
