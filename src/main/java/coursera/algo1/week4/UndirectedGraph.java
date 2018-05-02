package coursera.algo1.week4;

import java.util.*;

/**
 * @author Grigorev Alexey
 */
public class UndirectedGraph {
    private int n;
    private final ArrayList<List<Integer>> adj;
    private final ArrayList<List<Integer>> reverse;

    public UndirectedGraph(int n) {
        this.n = n;
        this.adj = createAdjacentList(n);
        this.reverse = createAdjacentList(n);
    }

    private static ArrayList<List<Integer>> createAdjacentList(int n) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>(n);

        int i = 0;
        while (i < n) {
            res.add(new LinkedList<Integer>());
            i++;
        }

        return res;
    }

    /**
     * Adds a directed edge from vertex v to vertex u
     * 
     * @param v vertex from
     * @param u vertex to
     */
    public void addEdge(int v, int u) {
        adj.get(v).add(u);
        reverse.get(u).add(v);
    }

    /**
     * Gets list of vertices adjacent to vertex v - connected with v by edges
     * 
     * @param v vertex
     * @return all adjacent vertices of v
     */
    public Iterable<Integer> adjacent(int v) {
        return adj.get(v);
    }

    /**
     * @param v
     * @return list of vertices which we can get from v 
     */
    public Iterable<Integer> reverse(int v) {
        return reverse.get(v);
    }

    public int getN() {
        return n;
    }
}
