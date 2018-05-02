package mccme_shad;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import codeforces.TestComparer;

import static org.testng.Assert.*;

public class Problem3080Test {

    @Test
    public void calcDistance() {
        int calcDistance = Problem3080.calcDistance(4, 6);
        assertEquals(calcDistance, 2);
    }

    @Test
    public void findFirstLocalMaximum() {
        int first = Problem3080.findFirstLocalMaximum(new int[] { 1, 2, 1, 1, 2, 1, 2, 1 });
        assertEquals(first, 1);
    }

    @Test
    public void findNextLocalMaximum() {
        int[] input = new int[] { 1, 2, 1, 1, 2, 1, 2, 1 };
        int next = Problem3080.findNextLocalMaximum(input, 2);
        assertEquals(next, 4);

        next = Problem3080.findNextLocalMaximum(input, next + 1);
        assertEquals(next, 6);

        next = Problem3080.findNextLocalMaximum(input, next + 1);
        assertFalse(Problem3080.withinBounds(next, input.length));
    }

    @Test(dataProvider = "tests")
    public void solve(int[] input, int expectedResult) {
        int res = Problem3080.solve(input);
        assertEquals(res, expectedResult);
    }

    @DataProvider
    public Object[][] tests() {
        return new Object[][] {
            { new int[] { 1, 2, 1, 1, 2, 1, 2, 1 }, 2 },
            { new int[] { 1, 2, 3 }, 0 },
            { new int[] { 1, 2, 3, 2 }, 0 },
            { new int[] { 1, 2, 2, 1 }, 0 },
            { new int[] { 1, 2, 1, 1, 2, 1 }, 3 },
            { new int[] { 1 }, 0 },
            { new int[] { }, 0 },
        };
    }

    @Test
    public void test() {
        TestComparer test = new TestComparer(new Problem3080());
        test.given("1\n2\n1\n1\n2\n1\n2\n1\n0");
        test.run();
        test.verifyOutput("2");
    }
    
}
