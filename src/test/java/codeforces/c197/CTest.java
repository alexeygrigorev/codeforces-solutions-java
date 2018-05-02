package codeforces.c197;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CTest {
    
    C sut = new C();
    
    @Test(dataProvider = "lines")
    public void calc(String input, String expectedOutput) {
        String result = sut.calc(input);
        assertEquals(result, expectedOutput);
    }
    
    @DataProvider
    public Object[][] lines() {
        return new Object[][] {
            { "ababba", "bbba" },
            { "abbcbccacbbcbaaba", "cccccbba" },
            { "abc", "c" },
            { "c", "c" },
            { "ccc", "ccc" },
            { "cccbbb", "cccbbb" },
            { "cacacababb", "cccbbb" },
        };
    }
}
