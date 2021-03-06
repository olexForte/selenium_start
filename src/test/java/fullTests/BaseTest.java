package fullTests;

//DataProvider imports
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.commons.lang3.ArrayUtils;

//TestNG imports
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.*;

//Test target package import
import automation.*;

public class BaseTest {

    static SearchPage page;

    String[] filenames = {"Costco","Walmart"};

    String[] defaultProps = {"targetSite","browserMode","searchCssSelector","searchSubmitCssSelector"};

    int dPIndex = defaultProps.length;

    @DataProvider(name = "getFromPropFiles")
    public Object[][] getFromPropFile (Method method){
        
        String[] propsToExtract = ArrayUtils.addAll(defaultProps, ((Test) method.getAnnotation(Test.class)).groups());

        Object[][] out = new Object[filenames.length][propsToExtract.length];

        try {

            for (int i = 0; i < filenames.length; i++){

                InputStream input = new FileInputStream("./src/test/resources/%s.properties".formatted(filenames[i]));

                Properties prop = new Properties();

                prop.load(input);

                for (int j = 0; j < propsToExtract.length; j++){
                    
                    out[i][j] = prop.getProperty(propsToExtract[j]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return out;
    }

    @BeforeMethod
    public void setup(Object[] testArgs){

        Object[] data = (Object[]) testArgs[0];

        page = new SearchPage((String) data[0], (String) data[1], (String) data[2],(String) data[3]);
    }

    @Test(
        dataProvider = "getFromPropFiles",
        groups = {
            "visitMessage",
            "visitExpect"
        }
    )
    public void visitTest (Object[] data){
        assertEquals((String) data[dPIndex + 0], data[dPIndex + 1], page.driver.getTitle());
    }

    /**
     * Searches the page for multiple objects.
     * @param data The data to be extracted from the property file.
     * @see #getFromPropFile(Method)
     */
    @Test(
        description = "Searches the page for multiple objects.",
        dataProvider = "getFromPropFiles",
        groups = {
            "searchExpectExitCode",
            "searchQueries",
            "verifyCssSelectors",
            "verifyExpectExitCodes"
        }
    )
    public void searchTest (Object[] data){
        
        SoftAssert searchTests = new SoftAssert();

        int searchExpectExitCode = Integer.parseInt((String) data[dPIndex + 0]);
        String[] searchQueries = ((String) data[dPIndex + 1]).split(",\s");
        String[] verifyCssSelectors = ((String) data[dPIndex + 2]).split(",\s");
        String[] expectExitCodes = ((String) data[dPIndex + 3]).split(",\s");

        for (int i = 0; i < verifyCssSelectors.length && i < expectExitCodes.length; i++){

            page.visit();
            
            try {
                
                page.search(searchQueries[i]);

                Integer actualExitCode = page.verify(verifyCssSelectors[i]);

                searchTests.assertEquals(actualExitCode.toString(), expectExitCodes[i], "Exit codes didn't match on a search!\nWebsite: %s\nSearch Query: %s\nError: ".formatted(data[0],searchQueries[i]));

            } catch (org.openqa.selenium.NoSuchElementException exception) {
                
                String message = exception.getMessage();
                
                searchTests.assertEquals(2, searchExpectExitCode, "The search method couldn't find what you were looking for! The exception says:\n\"(%s)\"\n".formatted(message));
            }
        }

        searchTests.assertAll("One or more of the search tests failed!");
    }

    @AfterMethod
    public void teardown (){
        page.closeTab();
        page.stop();
    }
}
