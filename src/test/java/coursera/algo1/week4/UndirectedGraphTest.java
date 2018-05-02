package coursera.algo1.week4;

import java.util.Arrays;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UndirectedGraphTest {
    @Test
    public void adjacentAndReverse() {
        UndirectedGraph graph = new UndirectedGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        assertEquals(graph.adjacent(1), Arrays.asList(2, 3));
        assertEquals(graph.reverse(3), Arrays.asList(1, 2));
    }
}
