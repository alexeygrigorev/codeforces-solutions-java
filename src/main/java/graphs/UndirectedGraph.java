package graphs;

import java.util.*;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UndirectedGraph {

    private static final Random RANDOM = new Random();

    private final Map<Integer, List<Integer>> vertices;

    public UndirectedGraph(int n) {
        this.vertices = createAdjacencyList(n);
    }

    private UndirectedGraph(Map<Integer, List<Integer>> vertices) {
        this.vertices = vertices;
    }

    private static Map<Integer, List<Integer>> createAdjacencyList(int n) {
        Map<Integer, List<Integer>> res = Maps.newHashMap();

        int i = 0;
        while (i < n) {
            res.put(i, new LinkedList<Integer>());
            i++;
        }

        return res;
    }

    public UndirectedGraph removeVertex(Integer v) {
        checkVertex(v);
        Map<Integer, List<Integer>> copy = copyVertices();
        removeVertexInternal(v, copy);
        return new UndirectedGraph(copy);
    }

    public UndirectedGraph removeVertices(Collection<Integer> nodes) {
        Map<Integer, List<Integer>> copy = copyVertices();
        for (Integer v : nodes) {
            removeVertexInternal(v, copy);
        }
        return new UndirectedGraph(copy);
    }

    private Map<Integer, List<Integer>> copyVertices() {
        return Maps.newHashMap(vertices);
    }

    private Map<Integer, List<Integer>> removeVertexInternal(Integer v, Map<Integer, List<Integer>> copy) {
        // TODO: may be optimized
        List<Integer> adjV = copy.get(v);
        for (Integer u : adjV) {
            List<Integer> adjU = Lists.newLinkedList(copy.get(u));
            adjU.remove(v);
            copy.put(u, adjU);
        }
        copy.remove(v);
        return copy;
    }

    public UndirectedGraph removeVertexWithAdjacent(Integer v) {
        checkVertex(v);
        Map<Integer, List<Integer>> copy = copyVertices();
        List<Integer> adjacent = copy.get(v);
        for (Integer u : adjacent) {
            removeVertexInternal(u, copy);
        }
        copy.remove(v);
        return new UndirectedGraph(copy);
    }

    public UndirectedGraph removeAllAdjacentTo(Integer v) {
        checkVertex(v);
        Map<Integer, List<Integer>> copy = copyVertices();
        List<Integer> adjacent = copy.get(v);
        for (Integer u : adjacent) {
            removeVertexInternal(u, copy);
        }
        return new UndirectedGraph(copy);
    }

    public int randomVertex() {
        List<Integer> keys = Lists.newArrayList(vertices.keySet());
        int item = RANDOM.nextInt(keys.size());
        return keys.get(item);
    }

    public List<Integer> allVertices() {
        return Lists.newArrayList(vertices.keySet());
    }

    public int size() {
        return vertices.size();
    }

    public boolean notEmpty() {
        return !vertices.isEmpty();
    }

    public void addEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);
        vertices.get(from).add(to);
        vertices.get(to).add(from);
    }

    public Collection<Integer> adjacent(Integer i) {
        checkVertex(i);
        return vertices.get(i);
    }

    public UndirectedGraph copy() {
        return new UndirectedGraph(copyVertices());
    }

    private void checkVertex(Integer i) {
        Validate.isTrue(vertices.containsKey(i));
    }

    public List<Integer> nodesOrderedByDegree() {
        List<Integer> result = allVertices();

        Collections.sort(result, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                List<Integer> first = vertices.get(o1);
                List<Integer> second = vertices.get(o2);
                return second.size() - first.size();
            }
        });

        return result;
    }

}
