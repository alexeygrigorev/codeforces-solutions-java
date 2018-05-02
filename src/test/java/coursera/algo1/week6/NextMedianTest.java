package coursera.algo1.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.LineIterator;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import coursera.algo1.week5.Heap;

import static org.testng.Assert.assertEquals;

/**
 * @author Grigorev Alexey
 */
public class NextMedianTest {

    @Test
    public void nextMedian() {
        List<Integer> stream = Arrays.asList(1, 2, 3, -1);
        NextMedian medians = new NextMedian(stream);

        assertEquals(medians.next().intValue(), 1);
        assertEquals(medians.next().intValue(), 1);
        assertEquals(medians.next().intValue(), 2);
        assertEquals(medians.next().intValue(), 1);
    }

    @Test
    public void heapsVsNaive() {
        Iterator<Integer> stream = readFromAssingmentFile();
        List<Integer> allInts = Lists.newArrayList(stream);
        NextMedian medians = new NextMedian(allInts);

        List<Integer> naiveMedians = Lists.newArrayList();

        for (Integer next : allInts) {
            naiveMedians.add(next);
            Collections.sort(naiveMedians);
            Integer naiveMedian = extractMedian(naiveMedians);

            assertEquals(medians.next(), naiveMedian);
        }
    }

    private Iterator<Integer> readFromAssingmentFile() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Median.txt")));
        return Iterators.transform(new LineIterator(reader), new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void extractMedianTest() {
        assertEquals(extractMedian(Arrays.asList(1, 2, 3, 4)).intValue(), 2);
        assertEquals(extractMedian(Arrays.asList(1, 2, 3, 4, 5)).intValue(), 3);
    }

    private Integer extractMedian(List<Integer> naiveMedians) {
        int size = naiveMedians.size();
        if (size % 2 == 0) {
            return naiveMedians.get(size / 2 - 1);
        } else {
            return naiveMedians.get(size / 2);
        }
    }

    @Test
    public void rebalanceHeapsFromLeftToRightOdd() {
        Heap<Integer, Integer> hLow = Heap.naturalMax();
        hLow.insert(1, 1);
        hLow.insert(3, 3);
        hLow.insert(4, 4);
        hLow.insert(5, 5);

        Heap<Integer, Integer> hHigh = Heap.naturalMin();
        hHigh.insert(6, 6);

        NextMedian.rebalanceHeaps(hLow, hHigh);

        assertEquals(hLow.peek().intValue(), 4);
        assertEquals(hHigh.peek().intValue(), 5);
    }

    @Test
    public void rebalanceHeapsFromRightToLeftEven() {
        Heap<Integer, Integer> hLow = Heap.naturalMax();
        hLow.insert(1, 1);

        Heap<Integer, Integer> hHigh = Heap.naturalMin();
        hHigh.insert(3, 3);
        hHigh.insert(4, 4);
        hHigh.insert(5, 5);
        hHigh.insert(6, 6);
        hHigh.insert(7, 7);

        NextMedian.rebalanceHeaps(hLow, hHigh);

        assertEquals(hLow.peek().intValue(), 4);
        assertEquals(hHigh.peek().intValue(), 5);
    }
}
