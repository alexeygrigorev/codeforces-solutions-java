package codeforces.c199;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CTest {
    
    C sut = new C();
    
    @Test(dataProvider = "inputs")
    public void calc(long k, long b, long n, long t, long expectedResult) {
        long res = sut.calc(k, b, n, t);
        assertEquals(res, expectedResult);
    }
    
    @DataProvider
    public Object[][] inputs() {
        return new Object[][] {
            { 3, 1, 3, 5, 2 },
            { 1, 4, 4, 7, 3 },
            { 2, 2, 4, 100, 0 },
            { 847, 374, 283, 485756, 282 },
            { 1, 1, 1000000, 1000000, 1 },
            { 126480, 295416, 829274, 421896, 829273 },
            { 999991, 5, 1000000, 999997, 999999 },
        };
    }
}
