package coursera.algo1.week1;

import java.util.Arrays;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MergeSortTest {

    MergeSort sort = new MergeSort();

    @Test
    public void merge() {
        int[] left = { 1, 3, 5 };
        int[] right = { 2, 3, 7, 9 };

        int[] result = sort.merge(left, right);
        int[] expected = { 1, 2, 3, 3, 5, 7, 9 };

        assertEquals(result, expected);
    }

    @Test
    public void mergeSort() {
        int[] array = { 3, 5, 9, 2, 3, 7, 1 };
        int[] expected = { 1, 2, 3, 3, 5, 7, 9 };

        int[] result = sort.mergeSort(array);

        assertEquals(result, expected);
    }

    @Test
    public void mergeSort2() {
        int[] array = { 2, 4, 3, 1, 4, 5, 6, 1, 7, 2, 7, 0, 3 };

        int[] expected = array.clone();
        Arrays.sort(expected);

        int[] result = sort.mergeSort(array);
        assertEquals(result, expected);
    }
}
