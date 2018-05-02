package coursera.algo1.week5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Undirected graph with weighted edges
 * 
 * @author Grigorev Alexey
 */
public class UndirectedWeightedGraph {
    private int n;
    private final List<List<Edge>> adj;

    public UndirectedWeightedGraph(int n) {
        this.n = n;
        this.adj = createAdjacentList(n);
    }

    private static List<List<Edge>> createAdjacentList(int n) {
        ArrayList<List<Edge>> res = new ArrayList<List<Edge>>(n);

        int i = 0;
        while (i < n) {
            res.add(new LinkedList<Edge>());
            i++;
        }

        return res;
    }

    public void addEdge(int v, int u, int weight) {
        adj.get(v).add(new Edge(v, u, weight));
        adj.get(u).add(new Edge(u, v, weight));
    }

    public Iterable<Edge> adjacent(int v) {
        return adj.get(v);
    }

    public int firstVertex() {
        return adj.get(0).get(0).getFrom();
    }

    public int getN() {
        return n;
    }
}