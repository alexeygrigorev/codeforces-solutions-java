package coursera.algo1.week2;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProblemATest {

    @Test
    public void sortIsCorrect() {
        ProblemA sut = new ProblemA();
        int[] actual = readInput("QuickSort.txt", 10000);

        int[] expected = Arrays.copyOf(actual, actual.length);

        Arrays.sort(expected);
        sut.countAndSort(actual);

        assertEquals(asList(actual), asList(expected));
    }

    private int[] readInput(String fileName, int len) {
        InputStream streamInput = this.getClass().getResourceAsStream(fileName);
        Scanner scanner = new Scanner(streamInput);

        int[] input = new int[len];

        int i = 0;
        while (scanner.hasNext()) {
            int nextInt = scanner.nextInt();
            input[i] = nextInt;
            i++;
        }

        return input;
    }

    @Test(dataProvider = "calcLenDataProvider")
    public void calcLen(int left, int right, int expectedLen) {
        ProblemA sut = new ProblemA();
        int length = sut.calcLength(left, right);
        assertEquals(length, expectedLen);
    }

    @DataProvider
    public Object[][] calcLenDataProvider() {
        return new Object[][] { { 0, 10, 10 }, { 5, 10, 5 }, { 0, 1, 1 }, { 2, 5, 3 }, };
    }

    @Test
    public void partition() {
        ProblemA sut = new ProblemA();

        int actual[] = { 3, 8, 2, 5, 1, 4 };
        int expected[] = { 1, 2, 3, 5, 8, 4 };

        int pivotIndex = sut.partition(actual, 0, actual.length);

        assertEquals(asList(actual), asList(expected));
        assertEquals(2, pivotIndex);
    }

    private static List<Integer> asList(int[] actual) {
        return Arrays.asList(ArrayUtils.toObject(actual));
    }

    @Test
    public void swap() {
        int actual[] = { 3, 8, 2, 5, 1, 4 };
        int expected[] = { 1, 8, 2, 5, 3, 4 };
        ProblemA.swap(actual, 0, 4);
        assertEquals(asList(actual), asList(expected));
    }

    @Test
    public void qsort() {
        ProblemA sut = new ProblemA();
        int actual[] = { 3, 8, 2, 5, 1, 4 };
        int expected[] = { 1, 2, 3, 4, 5, 8 };

        sut.qsort(actual, 0, actual.length);

        assertEquals(asList(actual), asList(expected));
    }

    @Test
    public void qsort2() {
        ProblemA sut = new ProblemA();
        int actual[] = { 13, 15, 5, 9, 1, 2, 12, 6, 7, 8, 11, 16, 14, 3, 4, 10 };
        int expected[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

        sut.qsort(actual, 0, actual.length);

        assertEquals(asList(actual), asList(expected));
    }

}
