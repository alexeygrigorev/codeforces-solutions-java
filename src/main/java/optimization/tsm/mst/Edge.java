package optimization.tsm.mst;

import optimization.tsm.Point;

import org.apache.commons.lang3.ObjectUtils;

public class Edge implements Comparable<Edge> {
    private final Point first;
    private final Point second;
    private final double d;

    public Edge(Point a, Point b) {
        this.first = a;
        this.second = b;
        this.d = a.squareDistanceTo(b);
    }

    public Point getFirst() {
        return first;
    }

    public Point getSecond() {
        return second;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.d, that.d);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Edge) {
            Edge that = (Edge) obj;
            return (ObjectUtils.equals(this.first, that.first) && ObjectUtils.equals(this.second, that.second))
                    || (ObjectUtils.equals(this.first, that.second) && ObjectUtils.equals(this.second, that.first));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return first.hashCode() + 31 * second.hashCode();
    }

    @Override
    public String toString() {
        return "Edge [d=" + d + ", " + first + ", " + second + "]";
    }
}