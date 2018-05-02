package coursera.algo2.week1;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;

import coursera.algo1.week5.Edge;
import coursera.algo1.week5.Heap;
import coursera.algo1.week5.Heap.HeapNode;
import coursera.algo1.week5.UndirectedWeightedGraph;

/**
 * @author Grigorev Alexey
 */
public class Prims {
    private final UndirectedWeightedGraph graph;
    private final SpanningTree spanningTree;
    private final List<HeapNode<Integer, Vertex>> nodes;
    private final Heap<Integer, Vertex> heap;

    private Prims(UndirectedWeightedGraph graph) {
        this.spanningTree = new SpanningTree(graph);
        this.graph = graph;

        this.heap = Heap.naturalMin();
        this.nodes = Lists.newArrayList();

        populateHeap();
    }

    private void populateHeap() {
        for (int i = 0; i < graph.getN(); i++) {
            HeapNode<Integer, Vertex> heapNode = heap.insert(i, new Vertex(i));
            nodes.add(heapNode);
        }
    }

    private SpanningTree execute() {
        int source = graph.firstVertex();
        spanningTree.addVertex(source);

        HeapNode<Integer, Vertex> sourceNode = nodes.get(source);
        heap.remove(sourceNode);
        recalculateKeys(source);

        while (true) {
            HeapNode<Integer, Vertex> first = heap.extractFirst();
            Vertex to = first.getKey();
            spanningTree.addEdge(to.minimalCostEdge);

            recalculateKeys(to.number);

            if (heap.isEmpty()) {
                Validate.isTrue(spanningTree.wholeGraphSpanned());
                return spanningTree;
            }
        }
    }

    private void recalculateKeys(int vertexFrom) {
        for (Edge e : graph.adjacent(vertexFrom)) {
            int to = e.getTo();
            HeapNode<Integer, Vertex> nodeTo = nodes.get(to);
            if (spanningTree.notSpanned(to)) {
                Vertex oldEdgeWeight = nodeTo.getKey();
                if (oldEdgeWeight.updateBestEdge(e)) {
                    heap.decreaseKey(nodeTo, oldEdgeWeight);
                }
            } else {
                heap.remove(nodeTo);
            }
        }
    }

    public static SpanningTree execute(UndirectedWeightedGraph graph) {
        return new Prims(graph).execute();
    }

    private static class Vertex implements Comparable<Vertex> {
        static final int INFINITY = 1000000;

        private final int number;
        private Edge minimalCostEdge;

        public Vertex(int number) {
            this.number = number;
        }

        private int weight() {
            if (minimalCostEdge == null) {
                return INFINITY;
            }
            return minimalCostEdge.getWeight();
        }

        /**
         * @param candidate potential new best edge
         * @return <code>true</code> if the best edge was updated, <code>false</code> otherwise
         */
        public boolean updateBestEdge(Edge candidate) {
            if (weight() > candidate.getWeight()) {
                minimalCostEdge = candidate;
                return true;
            }

            return false;
        }

        @Override
        public int compareTo(Vertex o) {
            return Utils.compareInt(weight(), o.weight());
        }

        @Override
        public String toString() {
            return "Vertex [number=" + number + ", weight=" + weight() + "]";
        }
    }

}
