package coursera.algo1.week2;

import notsandbox.Problem;

/**
 * The file contains all of the integers between 1 and 10,000 (inclusive, with no repeats) in unsorted order. The
 * integer in the ith row of the file gives you the ith entry of an input array.
 * 
 * Your task is to compute the total number of comparisons used to sort the given input file by QuickSort. As you know,
 * the number of comparisons depends on which elements are chosen as pivots, so we'll ask you to explore three different
 * pivoting rules. You should not count comparisons one-by-one. Rather, when there is a recursive call on a subarray of
 * length m, you should simply add m−1 to your running total of comparisons. (This is because the pivot element is
 * compared to each of the other m−1 elements in the subarray in this recursive call.)
 * 
 * WARNING: The Partition subroutine can be implemented in several different ways, and different implementations can
 * give you differing numbers of comparisons. For this problem, you should implement the Partition subroutine exactly as
 * it is described in the video lectures (otherwise you might get the wrong answer).
 * 
 * DIRECTIONS FOR THIS PROBLEM:
 * 
 * For the first part of the programming assignment, you should always use the first element of the array as the pivot
 * element.
 * 
 * @author Grigorev Alexey
 */
public class ProblemA extends Problem {

    private int numberOfComparisons;
    
    @Override
    public void run() {
        int[] input = readInput();
        countAndSort(input);
        out.println(numberOfComparisons);
    }

    public void countAndSort(int[] input) {
        qsort(input, 0, input.length);
    }

    /**
     * Your task is to compute the total number of comparisons used to sort the given input file by QuickSort. As you
     * know, the number of comparisons depends on which elements are chosen as pivots, so we'll ask you to explore three
     * different pivoting rules. You should not count comparisons one-by-one. Rather, when there is a recursive call on
     * a subarray of length m, you should simply add m−1 to your running total of comparisons. (This is because the
     * pivot element is compared to each of the other m−1 elements in the subarray in this recursive call.)
     */
    public void qsort(int[] input, int left, int right) {
        int n = calcLength(left, right);
        if (n <= 1) {
            return;
        }

        numberOfComparisons = numberOfComparisons + n - 1;

        pivotIndex(input, left, right);

        int pivotIndex = partition(input, left, right);

        qsort(input, left, pivotIndex);
        qsort(input, pivotIndex + 1, right);
    }

    public int calcLength(int left, int right) {
        return right - left;
    }

    public int partition(int[] input, int left, int right) {
        int pivot = input[left];
        int i = left + 1;

        for (int j = left + 1; j < right; j++) {
            if (input[j] < pivot) {
                swap(input, j, i);
                i++;
            }
        }

        swap(input, left, i - 1);
        return i - 1;
    }

    public static void swap(int[] input, int j, int i) {
        int tmp = input[j];
        input[j] = input[i];
        input[i] = tmp;
    }

    @SuppressWarnings("unused")
    public void pivotIndex(int[] input, int left, int right) {
        // no op
    }

    private int[] readInput() {
        int[] input = new int[10000];

        int i = 0;
        while (scanner.hasNext()) {
            int nextInt = scanner.nextInt();
            input[i] = nextInt;
            i++;
        }

        return input;
    }
}
