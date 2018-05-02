package coursera.algo1.week2;

/**
 * 
 * DIRECTIONS FOR THIS PROBLEM:
 * 
 * Compute the number of comparisons (as in Problem 1), always using the final element of the given array as the pivot
 * element. Again, be sure to implement the Partition subroutine exactly as it is described in the video lectures.
 * Recall from the lectures that, just before the main Partition subroutine, you should exchange the pivot element
 * (i.e., the last element) with the first element.
 * 
 * @author Grigorev Alexey
 * 
 */
public class ProblemB extends ProblemA {

    @Override
    public void pivotIndex(int[] input, int left, int right) {
        swap(input, left, right - 1);
    }

}
