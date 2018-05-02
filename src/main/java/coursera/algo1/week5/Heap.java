package coursera.algo1.week5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation is based on http://algorithms.soc.srcf.net/notes/dijkstra_with_heaps.pdf
 * 
 * @author Grigorev Alexey
 */
public class Heap<E, K> {

    private final List<HeapNode<E, K>> heap = new ArrayList<HeapNode<E, K>>();
    private final Comparator<K> comparator;

    /**
     * Use {@link #naturalMin()}, {@link #naturalMax()} or {@link #custom(Comparator)} for creating a heap
     * 
     * @param comparator to be used for comparisons
     */
    private Heap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    /**
     * Creates a heap with natural order of elements (i.e. heap which supports "extractMin" operation)
     */
    public static <E, K extends Comparable<K>> Heap<E, K> naturalMin() {
        return new Heap<E, K>(new Comparator<K>() {
            @Override
            public int compare(K o1, K o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * Creates a heap with reversed natural order of elements (i.e. heap which supports "extractMax" operation)
     */
    public static <E, K extends Comparable<K>> Heap<E, K> naturalMax() {
        return new Heap<E, K>(new Comparator<K>() {
            @Override
            public int compare(K o1, K o2) {
                return -o1.compareTo(o2);
            }
        });
    }

    /**
     * Creates a heap with a custom comparator
     */
    public static <E, K> Heap<E, K> custom(Comparator<K> comparator) {
        return new Heap<E, K>(comparator);
    }

    /**
     * Retrieves the stored value from the front, removes the front from the heap
     * 
     * @return the value stored in the front
     */
    public E pop() {
        return extractFirst().getValue();
    }

    /**
     * Retrieves the node from the front, removes the front from the queue
     * 
     * @return the front (first) node
     */
    public HeapNode<E, K> extractFirst() {
        ensureNotEmpty();

        HeapNode<E, K> first = heap.get(0);
        remove(first);

        return first;
    }

    public void remove(HeapNode<E, K> node) {
        if (node.orphan) {
            return;
        }

        int lastIndex = heap.size() - 1;
        HeapNode<E, K> last = heap.get(lastIndex);

        swap(node, last);
        heap.remove(lastIndex);

        increaseKey(last, last.getKey());
        node.orphan = true;
    }

    private void ensureNotEmpty() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("the heap is empty, cannot extract min");
        }
    }

    /**
     * Gets the value stored in the front without removing it from the heap
     * 
     * @return the front value
     */
    public E peek() {
        ensureNotEmpty();
        return heap.get(0).getValue();
    }

    /**
     * Depending on the new value of key, it either calls {@link #decreaseKey(HeapNode, Object)} or
     * {@link #increaseKey(HeapNode, Object)}
     * 
     * @param node
     * @param newKey
     */
    public void updateKey(HeapNode<E, K> node, K newKey) {
        if (lessThen(node.key, newKey)) {
            increaseKey(node, newKey);
        } else {
            decreaseKey(node, newKey);
        }
    }

    /**
     * Changes the value of the given key, keeping the heap balanced
     * 
     * @param node to increase the key
     * @param newKey new key value
     */
    public void increaseKey(HeapNode<E, K> node, K newKey) {
        node.setKey(newKey);

        while (isNotLeaf(node)) {
            HeapNode<E, K> minNode = minChildNode(node);

            if (minNode != null) {
                swap(node, minNode);
            } else {
                break;
            }
        }
    }

    private HeapNode<E, K> minChildNode(HeapNode<E, K> node) {
        HeapNode<E, K> minNode = null;
        K minKey = node.getKey();

        if (hasLeft(node)) {
            HeapNode<E, K> left = left(node);
            if (lessThen(left.getKey(), minKey)) {
                minNode = left;
                minKey = minNode.getKey();
            }
        }

        if (hasRigth(node)) {
            HeapNode<E, K> rigth = rigth(node);
            if (lessThen(rigth.getKey(), minKey)) {
                minNode = rigth;
                minKey = minNode.getKey();
            }
        }

        return minNode;
    }

    /**
     * Adds a new value to the heap, keeping it balanced
     * 
     * @param value new value
     * @param key associated key
     * @return node where newly added value is stored
     */
    public HeapNode<E, K> insert(E value, K key) {
        HeapNode<E, K> node = new HeapNode<E, K>(value, key, heap.size());
        heap.add(node);
        decreaseKey(node, key);
        return node;
    }

    /**
     * Changes the value of the given key, keeping the heap balanced
     * 
     * @param node to decrease the key
     * @param newKey new key value
     */
    public void decreaseKey(HeapNode<E, K> node, K newKey) {
        node.setKey(newKey);

        while (node.hasParent() && lessThen(newKey, parent(node).getKey())) {
            swap(node, parent(node));
        }
    }

    private HeapNode<E, K> parent(HeapNode<E, K> node) {
        return heap.get(node.parent());
    }

    private boolean lessThen(K left, K rigth) {
        return comparator.compare(left, rigth) < 0;
    }

    private void swap(HeapNode<E, K> node1, HeapNode<E, K> node2) {
        int node1Index = node1.getIndex();
        int node2Index = node2.getIndex();
        node1.setIndex(node2Index);
        node2.setIndex(node1Index);
        Collections.swap(heap, node1Index, node2Index);
    }

    /**
     * @return the size of the heap
     */
    public int size() {
        return heap.size();
    }

    private boolean isNotLeaf(HeapNode<E, K> node) {
        return node.left() < heap.size();
    }

    private boolean hasLeft(HeapNode<E, K> node) {
        return node.left() < heap.size();
    }

    private HeapNode<E, K> left(HeapNode<E, K> node) {
        return heap.get(node.left());
    }

    private boolean hasRigth(HeapNode<E, K> node) {
        return node.right() < heap.size();
    }

    private HeapNode<E, K> rigth(HeapNode<E, K> node) {
        return heap.get(node.right());
    }

    /**
     * @return <code>true</code> if the heap is empty, <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    /**
     * Holds the data that a heap node contains and plus some extra meta information like index and key.
     * 
     * @author Grigorev Alexey
     */
    // Implemented as an inner class to expose some private methods only to the heap
    public static class HeapNode<E, K> {

        private final E value;
        private K key;
        private int index;
        private boolean orphan = false;

        private HeapNode(E value, K key, int index) {
            this.value = value;
            this.key = key;
            this.index = index;
        }

        private int parent() {
            return (index - 1) / 2;
        }

        private int left() {
            return 2 * index + 1;
        }

        private int right() {
            return 2 * (index + 1);
        }

        private boolean hasParent() {
            return index != 0;
        }

        public E getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        private void setKey(K key) {
            this.key = key;
        }

        public int getIndex() {
            return index;
        }

        private void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "HeapNode [value=" + value + ", key=" + key + ", index=" + index + "]";
        }
    }
}