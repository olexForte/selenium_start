package TestNG;

//DataProvider imports
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Collections;

//TestNG imports
import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

//Test target package import
import automation.*;

public class BaseTest {

    static SearchPage page;

    @DataProvider(name = "entireFile")
    public Object[][] getPropFiles (){

        String[] filenames = {"Costco"};

        Object [][] out = new Object[filenames.length][0];

        try {

            for (int i = 0; i < filenames.length; i++){
                
                InputStream input = new FileInputStream("./src/test/resources/%s.properties".formatted(filenames[i]));

                Properties prop = new Properties();

                prop.load(input);

                //Gives me values as ArrayList and puts them into Object[][] out
                out[i] = Collections.list(prop.elements()).toArray();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return out;
    }

    @DataProvider(name = "partsOfFile")
    public Object[][] getFromPropFile (){
        
        String[] filenames = {"Costco"};

        String[] propsToExtract = {"site"};

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
        page = new SearchPage("https://costco.com", "chromeCap", "#search-field", "plastic spoon", "button.btn.search-ico-button");
    }

    @Test
    public void visit (){
        assertEquals("Costco was not visited!", page.driver.getTitle(), "Welcome to Costco Wholesale");
    }

    @Test
    public void searchTest (){
        page.search(page.objQuery);

        Integer expectExitCode = 0;

        Integer actualExitCode = page.verify("#search-results > div.product-list.grid > div:nth-child(1) > div.product-tile-set > div.thumbnail > div.caption.link-behavior > div.caption > span > a");
        
        assertEquals(expectExitCode, actualExitCode);
    }

    @AfterMethod
    public void teardown (){
        page.closeTab();
        page.stop();
    }
}
