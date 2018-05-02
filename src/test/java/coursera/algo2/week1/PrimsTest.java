package coursera.algo2.week1;

import org.testng.annotations.Test;

import coursera.algo1.week5.Edge;
import coursera.algo1.week5.UndirectedWeightedGraph;
import static org.testng.Assert.*;

/**
 * @author Grigorev Alexey
 */
public class PrimsTest {

    @Test
    public void graphFromLecture() {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(4);
        graph.addEdge(1, 0, 1);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 0, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(0, 2, 3);

        SpanningTree spanningTree = Prims.execute(graph);
        assertTrue(spanningTree.contains(new Edge(0, 1, 1)));
        assertTrue(spanningTree.contains(new Edge(1, 2, 2)));
        assertTrue(spanningTree.contains(new Edge(0, 3, 4)));
        assertEquals(spanningTree.cost(), 7L);
    }

    @Test
    public void graphFromForums() {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(5);
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 3);
        graph.addEdge(2, 3, 12);
        graph.addEdge(0, 3, -14);
        graph.addEdge(1, 3, 7);
        graph.addEdge(3, 4, -5);

        SpanningTree spanningTree = Prims.execute(graph);
        assertTrue(spanningTree.contains(new Edge(0, 1, 6)));
        assertTrue(spanningTree.contains(new Edge(0, 2, 3)));
        assertTrue(spanningTree.contains(new Edge(0, 3, -14)));
        assertTrue(spanningTree.contains(new Edge(3, 4, -5)));
        assertEquals(spanningTree.cost(), -10);
    }
}
