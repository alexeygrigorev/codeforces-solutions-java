package coursera.algo1.week2;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProblemBTest {

    @Test
    public void sortIsCorrect() {
        ProblemA sut = new ProblemB();
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

    private static List<Integer> asList(int[] actual) {
        return Arrays.asList(ArrayUtils.toObject(actual));
    }
}
