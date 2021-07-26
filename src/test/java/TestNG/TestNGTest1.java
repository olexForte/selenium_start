package TestNG;

import org.testng.annotations.*;
import static org.testng.AssertJUnit.*;

import automation.*;

public class TestNGTest1 {
    static SearchPage page;

    @BeforeMethod
    public void setup(){
        page = new SearchPage("https://costco.com", "chromeCap", "#search-field", "plastic spoon", "button.btn.search-ico-button");
    }

    @Test(description="checks if the correct website has been visited")
    public void visit (){
        assertEquals("Welcome to Costco Wholesale", page.driver.getTitle(), "Costco was not visited!");
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
