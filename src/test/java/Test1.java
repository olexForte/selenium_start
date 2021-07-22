import org.junit.jupiter.api.*;

import automation.*;

public class Test1 {
    
    static SearchPage page;

    @BeforeEach
    public void setup(){
        page = new SearchPage("https://costco.com", "chromeCap", "#search-field", "plastic spoon", "button.btn.search-ico-button");
    }

    @Test
    public void visit (){
        Assertions.assertEquals("Welcome to Costco Wholesale", page.driver.getTitle(), "Costco was not visited!");
    }

    @Test
    public void searchTest (){
        page.search(page.objQuery);

        Integer expectExitCode = 0;
        Integer actualExitCode = page.verify("#search-results > div.product-list.grid > div:nth-child(1) > div.product-tile-set > div.thumbnail > div.caption.link-behavior > div.caption > span > a");
        
        Assertions.assertEquals(expectExitCode, actualExitCode);
    }

    @AfterEach
    public void teardown (){
        page.closeTab();
        page.stop();
    }
}
