package coursera.algo2.week1;

import java.util.Set;

import com.google.common.collect.Sets;

import coursera.algo1.week5.Edge;
import coursera.algo1.week5.UndirectedWeightedGraph;

public class SpanningTree {

    private final UndirectedWeightedGraph graph;
    private final Set<Integer> usedVerticies = Sets.newHashSet();
    private final Set<Edge> tree = Sets.newHashSet();

    public SpanningTree(UndirectedWeightedGraph graph) {
        this.graph = graph;
    }

    public void addVertex(int vertex) {
        usedVerticies.add(vertex);
    }

    public void addEdge(Edge edge) {
        addVertex(edge.getTo());
        tree.add(edge);
    }

    public boolean spanned(int vertex) {
        return usedVerticies.contains(vertex);
    }

    public boolean notSpanned(int vertex) {
        return !spanned(vertex);
    }

    public boolean wholeGraphSpanned() {
        return usedVerticies.size() == graph.getN();
    }

    public boolean contains(Edge e) {
        return tree.contains(e);
    }

    public long cost() {
        long cost = 0;
        for (Edge e : tree) {
            cost = cost + e.getWeight();
        }
        return cost;
    }

}
