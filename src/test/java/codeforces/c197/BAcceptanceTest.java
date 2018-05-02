package codeforces.c197;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import codeforces.TestComparer;

public class BAcceptanceTest {

    private TestComparer test;

    @BeforeMethod
    public void setUp() {
        test = new TestComparer(new B());
    }
    
    @Test(dataProvider = "files") 
    public void test(String inputFile, String outputFile) {
        test.inputFromFile(inputFile);
        test.run();
        test.verifyFromFile(outputFile);
    }
    
    @DataProvider
    public Object[][] files() {
        return new Object[][] {
            { "b/001.in", "b/001.out" },
            { "b/002.in", "b/002.out" },
            { "b/003.in", "b/003.out" },
        };
    }

    
}
