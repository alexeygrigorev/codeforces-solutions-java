package optimization.knapsack;

import java.util.Arrays;
import java.util.List;

import optimization.knapsack.Knapsack.Item;
import optimization.knapsack.Knapsack.KnapsackResult;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import codeforces.TestComparer;

import static org.testng.Assert.*;

public class SolverTest {

    @Test(dataProvider = "stringInput")
    public void stringInputTest(String input, String output) {
        Solver sut = new Solver();
        TestComparer test = new TestComparer(sut);
        test.given(input);
        test.run();
        test.verifyOutput(output);
    }

    @DataProvider
    public Object[][] stringInput() {
        return new Object[][] { { "3 9\n" + "5 4\n" + "6 5\n" + "3 2", "11 1\n" + "1 1 0" },
                { "4 11\n" + "8 4\n" + "10 5\n" + "15 8\n" + "4 3\n", "19 1\n" + "0 0 1 1" },
                { "4 11\n" + "15 8\n" + "4 3\n" + "8 4\n" + "10 5\n", "19 1\n" + "1 1 0 0" } };
    }

    @Test(dataProvider = "files")
    public void expensive(String fileName, String outputFile) {
        Solver sut = new Solver();
        TestComparer test = new TestComparer(sut);
        test.inputFromFile(fileName);
        test.run();
        test.verifyFromFile(outputFile);
    }

    @DataProvider
    public Object[][] files() {
        return new Object[][] { 
            { "ks_400_0", "ks_400_0_output" },
            { "ks_10000_0", "ks_10000_0_output" },
        };
    }

    @Test
    public void overweightNotSelected() {
        int capacity = 10;
        Item[] items = { i(0, 10, 11), i(1, 11, 9), i(0, 5, 2) };
        Knapsack knapsack = new Knapsack(capacity, items);
        KnapsackResult result = knapsack.solve(new BranchAndBounds.Strategy());
        assertEquals(asList(result.getResult()), expected(0, 1, 0));
    }

    private Item i(int number, int value, int weight) {
        return new Item(number, value, weight);
    }

    private static List<Integer> asList(int[] actual) {
        return Arrays.asList(ArrayUtils.toObject(actual));
    }

    private static <E> List<E> expected(E... exp) {
        return Arrays.asList(exp);
    }
}
