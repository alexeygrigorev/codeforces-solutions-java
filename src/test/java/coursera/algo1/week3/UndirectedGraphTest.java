package coursera.algo1.week3;

import java.util.Arrays;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class UndirectedGraphTest {

    @Test
    public void contract() {
        UndirectedGraph graph = new UndirectedGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);

        graph.addEdge(2, 1);

        graph.contract(0, 1);

        assertEquals(graph.getN(), 2);
        assertEquals(graph.adjacentTo(2), Arrays.asList(0));
    }

    @Test
    public void randomVertex() {
        UndirectedGraph graph = new UndirectedGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(2, 1);

        graph.contract(0, 1);

        assertTrue(graph.randomEdge().getLeft() != 1);
        assertTrue(graph.randomEdge().getRight() != 1);
        assertTrue(graph.randomEdge().getLeft() != 1);
        assertTrue(graph.randomEdge().getRight() != 1);
        assertTrue(graph.randomEdge().getLeft() != 1);
        assertTrue(graph.randomEdge().getRight() != 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void contractThrowsIAEWhenAccessToRemoved() {
        UndirectedGraph graph = new UndirectedGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(2, 1);

        graph.contract(0, 1);
        graph.adjacentTo(1);
    }

}
