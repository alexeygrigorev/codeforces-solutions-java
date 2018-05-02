package optimization.tsm.mst;

import graphs.UndirectedGraph;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

import optimization.tsm.Point;
import optimization.tsm.Result;
import optimization.tsm.TspSolver;

import com.google.common.collect.Lists;

public class MST implements TspSolver {

    @Override
    public Result solve(List<Point> input) {
        List<Edge> edges = kruskal(input);
        SpanningTree tree = buildTree(input.size(), edges);

        List<Integer> eulerTraversal = tree.eulerTraversal();

        List<Point> path = toPath(input, eulerTraversal);

        return new Result(input, path, false);
    }

    public List<Edge> kruskal(List<Point> input) {
        PriorityQueue<Edge> edges = allEdges(input);

        List<DisjointSet<Point>> unionSet = makeSet(input);

        int size = input.size();
        List<Edge> result = Lists.newArrayList();
        while (!edges.isEmpty() && result.size() < size - 1) {
            Edge edge = edges.poll();

            DisjointSet<Point> cmp1 = find(unionSet, edge.getFirst());
            DisjointSet<Point> cmp2 = find(unionSet, edge.getSecond());

            if (!cmp1.getValue().equals(cmp2.getValue())) {
                cmp1.union(cmp2);
                result.add(edge);
            }
        }

        return result;
    }

    private List<DisjointSet<Point>> makeSet(List<Point> input) {
        List<DisjointSet<Point>> unionSet = Lists.newArrayListWithCapacity(input.size());
        for (Point p : input) {
            unionSet.add(new DisjointSet<Point>(p));
        }
        return unionSet;
    }

    private DisjointSet<Point> find(List<DisjointSet<Point>> unionSet, Point point) {
        return unionSet.get(point.getNumber()).find();
    }

    private PriorityQueue<Edge> allEdges(List<Point> input) {
        int size = input.size();
        PriorityQueue<Edge> heap = new PriorityQueue<Edge>(size * size);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Point a = input.get(i);
                Point b = input.get(j);
                heap.add(new Edge(a, b));
            }
        }
        return heap;
    }

    private SpanningTree buildTree(int n, List<Edge> edges) {
        UndirectedGraph graph = new UndirectedGraph(n);

        for (Edge e : edges) {
            Point first = e.getFirst();
            Point second = e.getSecond();
            graph.addEdge(first.getNumber(), second.getNumber());
        }

        return dfs(graph, 0, new boolean[n]);
    }

    private SpanningTree dfs(UndirectedGraph graph, int v, boolean[] visited) {
        SpanningTree tree = new SpanningTree(v);
        visited[v] = true;

        Collection<Integer> adj = graph.adjacent(v);

        for (int u : adj) {
            if (!visited[u]) {
                SpanningTree child = dfs(graph, u, visited);
                tree.addChild(child);
            }
        }

        return tree;
    }

    private List<Point> toPath(List<Point> input, List<Integer> eulerTraversal) {
        List<Point> path = Lists.newArrayListWithCapacity(eulerTraversal.size());
        for (int i : eulerTraversal) {
            path.add(input.get(i));
        }
        return path;
    }

}
