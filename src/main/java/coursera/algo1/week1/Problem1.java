package coursera.algo1.week1;

import java.util.Arrays;

import org.apache.commons.lang3.tuple.Pair;

import notsandbox.Problem;

/**
 * This file contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, with no integer
 * repeated.
 * 
 * Your task is to compute the number of inversions in the file given, where the ith row of the file indicates the ith
 * entry of an array. Because of the large size of this array, you should implement the fast divide-and-conquer
 * algorithm covered in the video lectures. The numeric answer for the given input file should be typed in the space
 * below. So if your answer is 1198233847, then just type 1198233847 in the space provided without any space / commas /
 * any other punctuation marks. 
 * 
 */
public class Problem1 extends Problem {

    @Override
    public void run() {
        int[] input = readInput();
        Pair<int[], Long> result = countAndSort(input);
        out.print(result.getRight());
    }

    public Pair<int[], Long> countAndSort(int[] input) {
        int n = input.length;
        if (n == 1) {
            return Pair.of(input, 0L);
        }

        int middle = n / 2;
        int leftUnsorted[] = Arrays.copyOfRange(input, 0, middle);
        int rightUnsorted[] = Arrays.copyOfRange(input, middle, n);

        Pair<int[], Long> left = countAndSort(leftUnsorted);
        Pair<int[], Long> right = countAndSort(rightUnsorted);
        Pair<int[], Long> split = countSplitAndMerge(left.getLeft(), right.getLeft());

        return Pair.of(split.getLeft(), left.getRight() + right.getRight() + split.getRight());
    }

    public Pair<int[], Long> countSplitAndMerge(int[] left, int[] right) {
        int leftLen = left.length, rightLen = right.length;
        int sortedOutput[] = new int[leftLen + rightLen];

        int leftIndex = 0, rightIndex = 0, resIndex = 0;

        long inversions = 0;

        while (leftIndex < leftLen && rightIndex < rightLen) {
            if (left[leftIndex] <= right[rightIndex]) {
                sortedOutput[resIndex] = left[leftIndex];
                leftIndex++;
                resIndex++;
                // nothing
            } else {
                sortedOutput[resIndex] = right[rightIndex];
                rightIndex++;
                resIndex++;

                int remainedInLeft = leftLen - leftIndex;
                inversions = inversions + remainedInLeft;
            }
        }

        while (leftIndex < leftLen) {
            sortedOutput[resIndex] = left[leftIndex];
            leftIndex++;
            resIndex++;
        }

        while (rightIndex < rightLen) {
            sortedOutput[resIndex] = right[rightIndex];
            rightIndex++;
            resIndex++;
        }

        return Pair.of(sortedOutput, inversions);
    }

    private int[] readInput() {
        int[] input = new int[100000];

        int i = 0;
        while (scanner.hasNext()) {
            int nextInt = scanner.nextInt();
            input[i] = nextInt;
            i++;
        }

        return input;
    }

}
