package coursera.algo1.week1;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import coursera.ProblemRunner;

import static org.testng.Assert.assertEquals;

public class Problem1Test {

    Problem1 sut = new Problem1();

    @Test
    public void countAndSort() {
        int[] array = { 1, 3, 5, 2, 4, 6 };
        Pair<int[], Long> result = sut.countAndSort(array);
        assertEquals(Long.valueOf(3), result.getRight());
    }

    @Test
    public void countAndSort2() {
        int[] array = { 1, 2, 6, 3, 5, 8, 7, 4 };
        Pair<int[], Long> result = sut.countAndSort(array);
        assertEquals(result.getRight(), Long.valueOf(7));
    }

    @Test
    public void countSplitAndMerge() {
        int[] left = { 1, 3, 5 };
        int[] right = { 2, 4, 6 };

        Pair<int[], Long> result = sut.countSplitAndMerge(left, right);

        assertEquals(result.getRight(), Long.valueOf(3));
    }

    @Test
    public void problem() {
        ProblemRunner test = new ProblemRunner(new Problem1());

        test.inputFromFile("IntegerArray.txt");
        test.run();

        System.out.println(test.getOutput());
    }
}
