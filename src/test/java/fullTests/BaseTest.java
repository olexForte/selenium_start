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

    String[] defaultProps = {"targetSite","browserMode"};

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

        page = new SearchPage((String) data[0], (String) data[1]);
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

    @Test(
        dataProvider = "getFromPropFiles",
        groups = {
            "searchQueries",
            "verifyCssSelectors",
            "expectExitCodes"
        }
    )
    public void searchTest (Object[] data){
        
        SoftAssert searchTests = new SoftAssert();

        String[] searchQueries = ((String) data[dPIndex + 0]).split(",\s");
        String[] verifyCssSelectors = ((String) data[dPIndex + 1]).split(",\s");
        String[] expectExitCodes = ((String) data[dPIndex + 2]).split(",\s");

        for (int i = 0; i < verifyCssSelectors.length && i < expectExitCodes.length; i++){

            page.visit();
            
            page.search(searchQueries[i]);

            Integer actualExitCode = page.verify("#%s".formatted(verifyCssSelectors[i]));

            searchTests.assertEquals(expectExitCodes[i], actualExitCode);
        }

        searchTests.assertAll("One of the search tests failed");
    }

    @AfterMethod
    public void teardown (){
        page.closeTab();
        page.stop();
    }
}
