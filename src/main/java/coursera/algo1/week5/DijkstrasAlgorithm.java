package coursera.algo1.week5;

import java.util.Arrays;
import java.util.List;

import coursera.algo1.week5.Heap.HeapNode;

/**
 * Implementation is based on http://algorithms.soc.srcf.net/notes/dijkstra_with_heaps.pdf
 * 
 * @author Grigorev Alexey
 */
public class DijkstrasAlgorithm {

    public static final int INFINITY = 1000000;

    public static int[] dijkstra(UndirectedWeightedGraph graph, int s) {
        int dist[] = new int[graph.getN()];
        Arrays.fill(dist, INFINITY);

        List<HeapNode<Integer, Integer>> heapNodesList = heapNodesList(graph.getN());

        Heap<Integer, Integer> heap = Heap.naturalMin();
        HeapNode<Integer, Integer> sourceHeapNode = heap.insert(s, 0);
        heapNodesList.set(s, sourceHeapNode);

        for (int i = 0; i < graph.getN(); i++) {
            if (i != s) {
                HeapNode<Integer, Integer> result = heap.insert(i, INFINITY);
                heapNodesList.set(i, result);
            }
        }

        int n = graph.getN();

        while (n > 0) {
            HeapNode<Integer, Integer> node = heap.extractFirst();
            int distance = node.getKey();
            int nodeNumber = node.getValue();
            dist[nodeNumber] = distance;

            for (Edge edge : graph.adjacent(nodeNumber)) {
                int to = edge.getTo();
                HeapNode<Integer, Integer> toNode = heapNodesList.get(to);
                int newDistance = distance + edge.getWeight();
                if (newDistance < toNode.getKey()) {
                    heap.decreaseKey(toNode, newDistance);
                }
            }

            n--;
        }

        return dist;
    }

    @SuppressWarnings("unchecked")
    private static List<HeapNode<Integer, Integer>> heapNodesList(int n) {
        List<?> list = Arrays.asList(new HeapNode[n]);
        return (List<HeapNode<Integer, Integer>>) list;
    }

}
