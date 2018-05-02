package graphs.maxindset;

import graphs.UndirectedGraph;

import java.util.Set;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.google.common.collect.ImmutableSet;

public class RandomizedBBSearchTest {

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

        Set<Integer> result = RandomizedBBSearch.solve(graph);
        System.out.println(result);
        assertTrue(result.size() == 2);
    }

    @Test // probabilistic, may fail
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

        Set<Integer> result = RandomizedBBSearch.solve(graph);
        System.out.println(result);
        assertEquals(result, ImmutableSet.of(1, 3, 5, 7, 8));
    }
}
