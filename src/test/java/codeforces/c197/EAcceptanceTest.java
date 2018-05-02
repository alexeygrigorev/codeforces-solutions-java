package codeforces.c197;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import codeforces.TestComparer;

public class EAcceptanceTest {

    private TestComparer test;

    @BeforeMethod
    public void setUp() {
        test = new TestComparer(new E());
    }
    
    @Test(dataProvider = "files") 
    public void test(String inputFile, String output) {
        test.inputFromFile(inputFile);
        test.run();
        test.verifyOutput(output);
    }
    
    @DataProvider
    public Object[][] files() {
        return new Object[][] {
            { "e/001.in", "1 3 2" },
            { "e/002.in", "4 2 1 3" },
        };
    }
}
