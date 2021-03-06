package codeforces.c197;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import codeforces.TestComparer;

public class DAcceptanceTest {

    private TestComparer test;

    @BeforeMethod
    public void setUp() {
        test = new TestComparer(new D());
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
            { "d/001.in", "YES" },
            { "d/002.in", "YES" },
            { "d/003.in", "NO" },
            { "d/004.in", "NO" },
            { "d/005.in", "YES" },
        };
    }
}
