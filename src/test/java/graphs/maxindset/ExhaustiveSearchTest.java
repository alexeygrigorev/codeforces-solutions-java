package graphs.maxindset;

import graphs.UndirectedGraph;

import java.util.Set;

import org.testng.annotations.Test;

public class ExhaustiveSearchTest {

    @Test
    public void solve() {
        UndirectedGraph graph = new UndirectedGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 0);

        Set<Set<Integer>> result = new ExhaustiveSearch().solve(graph);
        System.out.println(result);
    }

    @Test
    public void solve2() {
        UndirectedGraph graph = new UndirectedGraph(9);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 0);
        graph.addEdge(0, 8);
        graph.addEdge(2, 8);
        graph.addEdge(4, 8);
        graph.addEdge(6, 8);

        Set<Set<Integer>> result = new ExhaustiveSearch().solve(graph);
        System.out.println(result);
    }
}
