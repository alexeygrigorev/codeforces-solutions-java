package optimization.tsm.mst;

public class DisjointSet<E> {

    private DisjointSet<E> parent;
    private final E value;
    private int rank;

    public DisjointSet(E value) {
        this.value = value;
        this.parent = this;
        this.rank = 0;
    }

    public DisjointSet(E value, DisjointSet<E> parent) {
        this.value = value;
        this.parent = parent;
    }

    public DisjointSet<E> find() {
        if (parent != this) {
            parent = parent.find();
        }
        return parent;
    }

    public DisjointSet<E> union(DisjointSet<E> other) {
        DisjointSet<E> thisParent = this.find();
        DisjointSet<E> otherParent = other.find();

        if (thisParent.equals(otherParent)) {
            return thisParent;
        }

        if (thisParent.rank > otherParent.rank) {
            otherParent.parent = thisParent;
            return thisParent;
        } else {
            thisParent.parent = otherParent;
            otherParent.rank++;
            return otherParent;
        }
    }

    public E getValue() {
        return value;
    }

}