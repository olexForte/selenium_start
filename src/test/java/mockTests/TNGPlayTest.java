package mockTests;

import org.testng.annotations.*;

public class TNGPlayTest {
    
    @Test
    public void EasyTest(){
        System.out.println("EasyTest ran");
    }

    //This method will provide data to any test method that declares that its Data Provider
    //is named "tngPlayground"
    @DataProvider(name = "tngPlayground")
    public Object[][] createData1() {
        return new Object[][] {
            { "Cedric", Integer.valueOf(36), "Barber"},
            { "Anne", Integer.valueOf(37), "Chef"},
            { "Big Smoke", Integer.valueOf(45), "Memester"}
        };
    }
    
    //This test method declares that its data should be supplied by the Data Provider
    //named "tngPlayground"
    @Test(dataProvider = "tngPlayground")
    public void verifyData1(String n1, Integer n2, String n3) {
    System.out.println(n1 + " " + n2 + " " + n3);
    }

}
