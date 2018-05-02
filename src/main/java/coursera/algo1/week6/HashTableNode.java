package coursera.algo1.week6;

public class HashTableNode {

    private final int value;
    private HashTableNode next;

    public HashTableNode(HashTableNode next, int value) {
        this.next = next;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public HashTableNode getNext() {
        return next;
    }

    public void setNext(HashTableNode next) {
        this.next = next;
    }

}
