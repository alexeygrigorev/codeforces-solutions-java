package coursera.algo1.week2;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProblemCTest {

    @Test
    public void sortIsCorrect() {
        ProblemC sut = new ProblemC();
        int[] actual = readInput("QuickSort.txt", 10000);

        int[] expected = copy(actual);

        Arrays.sort(expected);
        sut.countAndSort(actual);

        assertEquals(asList(actual), asList(expected));
    }

    private int[] copy(int[] actual) {
        int[] expected = Arrays.copyOf(actual, actual.length);
        return expected;
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

    private static List<Integer> asList(int[] actual) {
        return Arrays.asList(ArrayUtils.toObject(actual));
    }

    @Test
    public void pivotIndex() {
        ProblemC sut = new ProblemC();
        int actual[] = { 8, 2, 4, 5, 7, 1 };
        int expected[] = { 4, 2, 8, 5, 7, 1 };

        sut.pivotIndex(actual, 0, actual.length);
        
        assertEquals(asList(actual), asList(expected));
    }

}
