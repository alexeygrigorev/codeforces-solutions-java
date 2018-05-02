package coursera.algo1.week5;


/**
 * A weighted edge for {@link UndirectedWeightedGraph}
 * 
 * @author Grigorev Alexey
 */
public class Edge {

    private final int from;
    private final int to;
    private final int weight;

    public Edge(int from, int to, int weigth) {
        this.from = from;
        this.to = to;
        this.weight = weigth;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return obj instanceof Edge && equals((Edge) obj);
    }

    public boolean equals(Edge obj) {
        if (this.weight != obj.weight) {
            return false;
        }

        boolean direct = this.to == obj.to && this.from == obj.from;
        boolean inv = this.to == obj.from && this.from == obj.to;

        return direct || inv;
    }

    @Override
    public int hashCode() {
        return from + to + 31 * weight;
    }

}