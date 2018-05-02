package coursera.algo1.week3;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableMap;

/**
 * @author Grigorev Alexey
 */
public class UndirectedGraph {
    private int n;
    private final Map<Integer, List<Integer>> adj;
    private final Random random = new Random();

    public UndirectedGraph(int n) {
        this.n = n;
        this.adj = createAdjacentList(n);
    }

    private static Map<Integer, List<Integer>> createAdjacentList(int n) {
        Map<Integer, List<Integer>> res = new LinkedHashMap<Integer, List<Integer>>();

        int i = 0;
        while (i < n) {
            res.put(i, new ArrayList<Integer>());
            i++;
        }

        return res;
    }

    /**
     * Copy constructor
     * @param copy
     */
    private UndirectedGraph(UndirectedGraph copy) {
        this.n = copy.n;
        this.adj = new LinkedHashMap<Integer, List<Integer>>();
        
        for (Entry<Integer, List<Integer>> entry : copy.adj.entrySet()) {
            adj.put(entry.getKey(), new ArrayList<Integer>(entry.getValue()));
        }
    }
    
    /**
     * @return deep copy of the graph
     */
    public UndirectedGraph copy() {
        return new UndirectedGraph(this);
    }

    public void contract(int v, int u) {
        List<Integer> newList = new ArrayList<Integer>();

        for (int fromFirst : adj.get(v)) {
            if (fromFirst != u) {
                newList.add(fromFirst);
            }
        }

        for (int fromSecond : adj.get(u)) {
            if (fromSecond != v) {
                newList.add(fromSecond);
            }
        }

        adj.remove(v);
        adj.remove(u);
        n--;

        // updating the graph so 'u' now will point to 'v'
        for (Entry<Integer, List<Integer>> entry : adj.entrySet()) {
            List<Integer> row = entry.getValue();
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).intValue() == u) {
                    row.set(i, v);
                }
            }
        }

        // and keeping only 'v'
        adj.put(v, newList);
    }

    /**
     * Adds a directed edge from vertex v to vertex u
     * 
     * @param v vertex from
     * @param u vertex to
     */
    public void addEdge(int v, int u) {
        adj.get(v).add(u);
    }

    /**
     * Gets list of vertices adjacent to vertex v - connected with v by edges
     * 
     * @param v vertex
     * @return all adjacent vertices of v
     */
    public Iterable<Integer> adjacentTo(int v) {
        if (!adj.containsKey(v)) {
            throw new IllegalArgumentException(v + " is already removed");
        }
        return adj.get(v);
    }

    /**
     * @return randomly selected available vertex
     */
    // TODO may be implemented more efficiently
    public Pair<Integer, Integer> randomEdge() {
        int[] available = new int[n];

        int j = 0;
        for (Entry<Integer, List<Integer>> entry : adj.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                available[j] = entry.getKey();
                j++;
            }
        }

        int vertexU = available[random.nextInt(j)];
        List<Integer> edges = adj.get(vertexU);
        int vertexV = edges.get(random.nextInt(edges.size()));
        return Pair.of(vertexU, vertexV);
    }

    public Map<Integer, List<Integer>> adjacencyList() {
        return ImmutableMap.copyOf(adj);
    }
    
    public int getN() {
        return n;
    }
}
