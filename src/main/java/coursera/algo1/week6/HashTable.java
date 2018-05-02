package coursera.algo1.week6;

public class HashTable {

    private final int buckets;
    private HashTableNode[] hashTable;

    public HashTable(int buckets) {
        this.buckets = buckets;
        this.hashTable = new HashTableNode[buckets];
    }

    public void add(int i) {
        if (!contains(i)) {
            int hash = h(i);
            HashTableNode oldNode = hashTable[hash];
            hashTable[hash] = new HashTableNode(oldNode, i);
        }
    }

    public void remove(int i) {
        if (!contains(i)) {
            return;
        }

        int hash = h(i);
        HashTableNode prevNode = hashTable[hash];

        if (prevNode.getValue() == i) {
            hashTable[hash] = prevNode.getNext();
            return;
        }

        HashTableNode node = prevNode.getNext();
        while (node != null) {
            if (node.getValue() == i) {
                prevNode.setNext(node.getNext());
                return;
            }

            prevNode = node;
            node = node.getNext();
        }
    }

    public boolean contains(int i) {
        HashTableNode node = hashTable[h(i)];
        while (node != null) {
            if (node.getValue() == i) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    private int h(int number) {
        return Math.abs(number) % buckets;
    }
}
