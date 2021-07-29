package mockTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Collections;

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
        String[] filenames = {"data1", "data2"};

        Object [][] out = new Object[filenames.length][0];


        try {
            
            for (int i = 0; i < filenames.length; i++){
                
                String filepath = "./src/test/resources/%s.properties".formatted(filenames[i]);

                InputStream input = new FileInputStream(filepath);

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
    
    //This test method declares that its data should be supplied by the Data Provider
    //named "tngPlayground"
    @Test(dataProvider = "tngPlayground")
    public void verifyData1(String n1, Integer n2, String n3) {
    System.out.println(n1 + " " + n2 + " " + n3);
    }
}
