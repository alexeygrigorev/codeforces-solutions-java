package coursera.algo1.week5;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.testng.collections.Lists;

import notsandbox.Problem;

/**
 * In this programming problem you'll code up Dijkstra's shortest-path algorithm.
 * 
 * The file contains an adjacency list representation of an undirected weighted graph with 200 vertices labeled 1 to
 * 200. Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that
 * edge. For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6.
 * The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has length
 * 8200. kThe rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths of the
 * corresponding edges.
 * 
 * Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the source
 * vertex, and to compute the shortest-path distances between 1 and every other vertex of the graph. If there is no path
 * between a vertex v and vertex 1, we'll define the shortest-path distance between 1 and v to be 1000000.
 * 
 * You should report the shortest-path distances to the following ten vertices, in order:
 * 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a comma-separated string of integers. So if you
 * find that all ten of these vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away,
 * then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of reporting DOES
 * MATTER, and the string should be in the same order in which the above ten vertices are given. Please type your answer
 * in the space provided.
 * 
 * IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of Dijkstra's
 * algorithm should work fine.
 * 
 * OPTIONAL: For those of you seeking an additional challenge, try implementing the heap-based version. Note this
 * requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices
 * and their positions in the heap.
 * 
 * @author Grigorev Alexey
 * 
 */
public class Problem1 extends Problem {

    private int source = 0;
    private List<Integer> toVertices = Arrays.asList(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);

    @Override
    public void run() {
        UndirectedWeightedGraph graph = readGraph(200);
        int[] distances = DijkstrasAlgorithm.dijkstra(graph, source);

        List<Integer> result = Lists.newArrayList(toVertices.size());

        for (Integer vertex : toVertices) {
            result.add(distances[vertex - 1]);
        }

        out.println(StringUtils.join(result, ","));
    }

    public UndirectedWeightedGraph readGraph(int n) {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(n);
        LineIterator it = new LineIterator(reader);
        while (it.hasNext()) {
            String line = it.next();
            String[] input = line.split("\\s+");

            int v = Integer.parseInt(input[0]);

            for (int i = 1; i < input.length; i++) {
                String[] edge = input[i].split(",");
                int u = Integer.parseInt(edge[0]);
                int weight = Integer.parseInt(edge[1]);
                graph.addEdge(v - 1, u - 1, weight);
            }
        }
        it.close();
        return graph;
    }

    public void setToVertices(Integer... toVertices) {
        this.toVertices = Arrays.asList(toVertices);
    }

    public void setSource(int source) {
        this.source = source - 1;
    }

}
