package coursera.algo1.week1;

import java.util.Arrays;

public class MergeSort {

    public int[] mergeSort(int[] input) {
        int n = input.length;
        if (n <= 1) {
            return input;
        }

        int middle = n / 2;
        int leftUnsorted[] = Arrays.copyOfRange(input, 0, middle);
        int rightUnsorted[] = Arrays.copyOfRange(input, middle, n);

        int left[] = mergeSort(leftUnsorted);
        int right[] = mergeSort(rightUnsorted);

        return merge(left, right);
    }

    public int[] merge(int[] left, int[] right) {
        int leftLen = left.length, rightLen = right.length;
        int res[] = new int[leftLen + rightLen];

        int leftIndex = 0, rightIndex = 0, resIndex = 0;

        while (leftIndex < leftLen && rightIndex < rightLen) {
            if (left[leftIndex] <= right[rightIndex]) {
                res[resIndex] = left[leftIndex];
                leftIndex++;
                resIndex++;
            } else {
                res[resIndex] = right[rightIndex];
                rightIndex++;
                resIndex++;
            }
        }

        while (leftIndex < leftLen) {
            res[resIndex] = left[leftIndex];
            leftIndex++;
            resIndex++;
        }

        while (rightIndex < rightLen) {
            res[resIndex] = right[rightIndex];
            rightIndex++;
            resIndex++;
        }

        return res;
    }

}
