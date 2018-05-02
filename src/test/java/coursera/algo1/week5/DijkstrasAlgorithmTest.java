package coursera.algo1.week5;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * @author Grigorev Alexey
 */
public class DijkstrasAlgorithmTest {

    @Test
    public void dijkstraUnconnectedNodes() {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 1);

        int[] result = DijkstrasAlgorithm.dijkstra(graph, 0);

        assertEquals(asList(result), Arrays.asList(0, 1, DijkstrasAlgorithm.INFINITY, DijkstrasAlgorithm.INFINITY));
    }

    private static List<Integer> asList(int[] actual) {
        return Arrays.asList(ArrayUtils.toObject(actual));
    }
}
