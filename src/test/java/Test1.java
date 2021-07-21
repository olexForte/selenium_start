import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import automation.Page;

public class Test1 {
    
    static Page page;

    @BeforeEach
    public static void setup(){
        page = new Page("https://costco.com", "chromeCap");
    }

    @Test
    public void visit (){
        assertEquals("Costco was not visited!", "Welcome to Costco Wholesale", page.driver.getTitle());
    }

    @AfterEach
    public static void teardown (){
        page.stop();
    }
}
