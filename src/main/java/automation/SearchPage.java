package automation;

public class SearchPage extends Page {
    
    String objSearchCssSelector;

    String objQuery;

    String objSubmitCssSelector;

    /**
     * Initializes a Page equipped with search features.
     * @param site The site you would like to open.
     * @param browser (Optional) The browser you would like to use. (Default) The default defined in the {@link DriverProvider} class.
     * @param searchCssSelector The CSS selector targeting the search field you would like to type to.
     * @param query The string you would like to type.
     * @param submitCssSelector The CSS selector targeting the submit button.
     */
    public SearchPage (String site, String browser, String searchCssSelector, String query, String submitCssSelector){
        super(site, browser);

        objSearchCssSelector = searchCssSelector;
        objQuery = query;
        objSubmitCssSelector = submitCssSelector;
    }
    /**
     * Initialize a Page equipped with search features. (Default) The default defined in the {@link DriverProvider} class.
     * @param site The site you would like to open.
     * @param searchCssSelector The CSS selector targeting the search field you would like to type to.
     * @param query The string you would like to type.
     * @param submitCssSelector The CSS selector targeting the submit button.
     */
    public SearchPage (String site, String searchCssSelector, String query, String submitCssSelector){
        super(site);

        objSearchCssSelector = searchCssSelector;
        objQuery = query;
        objSubmitCssSelector = submitCssSelector;
    }

    /**
     * Search a Page.
     * @param searchCssSelector (Optional) The CSS selector targeting the search field you would like to type to. (Default) The default defined in the {@link #objSearchCssSelector} variable.
     * @param query The string you would like to type.
     * @param submitCssSelector (Optional) The CSS selector targeting the submit button. (Default) The default defined in the {@link #objSubmitCssSelector} variable.
     */
    public void search (String searchCssSelector, String query, String submitCssSelector){
        typeIn(searchCssSelector, query);
        clickOn(submitCssSelector);
    }
    /**
     * Search a Page. (Defaults) The defaults defined in the {@link #objSearchCssSelector} and {@link #objSubmitCssSelector} variables.
     * @param query The string you would like to type.
     */
    public void search (String query){
        typeIn(objSearchCssSelector, query);
        clickOn(objSubmitCssSelector);
    }
}
