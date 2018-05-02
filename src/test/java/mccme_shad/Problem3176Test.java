package mccme_shad;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import codeforces.TestComparer;

import static org.testng.Assert.assertEquals;

public class Problem3176Test {

    @Test(dataProvider = "tests")
    public void solve(List<Queen> queens, boolean expectedResult) {
        Problem3176_8Queens problem = new Problem3176_8Queens();
        boolean actualResult = problem.solve(queens);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] tests() {
        return new Object[][] {
            { queens(q(1, 7), q(2, 4), q(3, 2), q(4, 8), q(5, 6), q(6, 1), q(7, 3), q(8, 5)), true },
            { queens(q(1, 8), q(2, 7), q(3, 6), q(4, 5), q(5, 4), q(6, 3), q(7, 2), q(8, 1)), false },
        };
    }

    private static List<Queen> queens(Queen... a) {
        return Arrays.asList(a);
    }

    private static Queen q(int x, int y) {
        return new Queen(x - 1, y - 1);
    }

    @Test(dataProvider = "files") 
    public void test(String inputFile, String output) {
        TestComparer test = new TestComparer(new Problem3176_8Queens());
        test.inputFromFile(inputFile);
        test.run();
        test.verifyOutput(output);
    }

    @DataProvider
    public Object[][] files() {
        return new Object[][] {
            { "3176/001.in", "NO" },
            { "3176/002.in", "YES" },
        };
    }

}
