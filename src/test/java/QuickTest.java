import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class QuickTest {
    
    static int val1, val2;

    //@BeforeAll
    public static void setUp (){
        val1 = 2;
        val2 = 3;
    }

    //@Test
    public void testAdd (){
        double result = val1 + val2;
        assertTrue(result == 5, "Result is not equal to 5.");
    }
}
