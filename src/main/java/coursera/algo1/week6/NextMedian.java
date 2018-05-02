package coursera.algo1.week6;

import java.util.Iterator;

import coursera.algo1.week5.Heap;
import coursera.algo1.week5.Heap.HeapNode;

/**
 * Iterator which returns next median as long as there are elements in the underlying stream.
 * 
 * @author Grigorev Alexey
 */
public class NextMedian implements Iterator<Integer> {

    private final Iterator<Integer> stream;
    private final Heap<Integer, Integer> hHigh;
    private final Heap<Integer, Integer> hLow;
    private int median = 0;

    public NextMedian(Iterator<Integer> stream) {
        this.stream = stream;
        this.hHigh = Heap.naturalMin();
        this.hLow = Heap.naturalMax();
    }

    public NextMedian(Iterable<Integer> stream) {
        this(stream.iterator());
    }

    @Override
    public boolean hasNext() {
        return stream.hasNext();
    }

    @Override
    public Integer next() {
        int next = stream.next();
        if (next <= median) {
            hLow.insert(next, next);
        } else {
            hHigh.insert(next, next);
        }

        rebalanceHeaps(hLow, hHigh);
        median = hLow.peek();
        return median;
    }

    /**
     * Ensures that
     * <ul>
     * <li>the heaps are equal in size - when the cumulative size of both is even and</li>
     * <li>left heap is one element longer - when the cumulative size is odd.</li>
     * </ul>
     * 
     * @param left left heap with extractMax operation
     * @param rigth right heap with extractMin operation
     */
    public static void rebalanceHeaps(Heap<Integer, Integer> left, Heap<Integer, Integer> rigth) {
        while (left.size() - 1 > rigth.size()) {
            HeapNode<Integer, Integer> maxFromLeft = left.extractFirst();
            rigth.insert(maxFromLeft.getValue(), maxFromLeft.getKey());
        }

        while (left.size() < rigth.size()) {
            HeapNode<Integer, Integer> minFromRight = rigth.extractFirst();
            left.insert(minFromRight.getValue(), minFromRight.getKey());
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
